package com.toolsjavachallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.toolsjavachallenge.controller.PagamentoCtrl;
import com.toolsjavachallenge.dto.request.DescricaoRequestDTO;
import com.toolsjavachallenge.dto.request.FormaPagamentoRequestDTO;
import com.toolsjavachallenge.dto.request.PagamentoRequestDTO;
import com.toolsjavachallenge.dto.request.TransacaoRequestDTO;
import com.toolsjavachallenge.dto.request.enums.TipoPagamentoRequest;
import com.toolsjavachallenge.dto.response.DescricaoResponseDTO;
import com.toolsjavachallenge.dto.response.PagamentoResponseDTO;
import com.toolsjavachallenge.dto.response.TransacaoResponseDTO;
import com.toolsjavachallenge.service.PagamentoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PagamentoCtrlTest {

    private MockMvc mockMvc;

    @Mock
    private PagamentoService service;

    @InjectMocks
    private PagamentoCtrl controller;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        this.objectMapper = new ObjectMapper();
        // Importante: Registrar módulo para suportar LocalDateTime dos seus DTOs
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void deveAutorizarPagamentoComSucesso() throws Exception {
        DescricaoRequestDTO descricaoReq = DescricaoRequestDTO.builder()
                .valor(new BigDecimal("100.00"))
                .dataHora(LocalDateTime.now())
                .estabelecimento("Loja Teste")
                .build();

        FormaPagamentoRequestDTO formaPagamentoReq = new FormaPagamentoRequestDTO();
        formaPagamentoReq.setTipo(TipoPagamentoRequest.AVISTA);
        formaPagamentoReq.setParcelas(1);

        TransacaoRequestDTO transacaoReq = new TransacaoRequestDTO();
        transacaoReq.setId(12345L);
        transacaoReq.setCartao("4444555566667777");
        transacaoReq.setDescricao(descricaoReq);
        transacaoReq.setFormaPagamento(formaPagamentoReq);

        PagamentoRequestDTO payload = new PagamentoRequestDTO();
        payload.setTransacao(transacaoReq);

        PagamentoResponseDTO responseDTO = PagamentoResponseDTO.builder()
                .transacao(TransacaoResponseDTO.builder()
                        .id(12345L)
                        .cartao("4444555566667777")
                        .build())
                .build();

        when(service.processarPagamento(any(PagamentoRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/pagamentos/autorizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transacao.id").value(12345))
                .andExpect(jsonPath("$.transacao.cartao").value("4444555566667777"));
    }

    @Test
    public void deveBuscarPagamentoPorId() throws Exception {
        PagamentoResponseDTO responseDTO = PagamentoResponseDTO.builder()
                .transacao(TransacaoResponseDTO.builder().id(1L).build())
                .build();

        when(service.buscarPorId(1L)).thenReturn(responseDTO);

        mockMvc.perform(get("/pagamentos/buscar/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value(1));
    }

    @Test
    public void deveListarTodosOsPagamentos() throws Exception {
        PagamentoResponseDTO responseDTO = PagamentoResponseDTO.builder()
                .transacao(TransacaoResponseDTO.builder().id(1L).build())
                .build();

        when(service.buscarTodas()).thenReturn(Collections.singletonList(responseDTO));

        mockMvc.perform(get("/pagamentos/listartodos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transacao.id").value(1));
    }

    @Test
    public void deveEstornarPagamentoComSucesso() throws Exception {
        Long idTransacao = 100L;

        PagamentoResponseDTO responseDTO = PagamentoResponseDTO.builder()
                .transacao(TransacaoResponseDTO.builder()
                        .id(idTransacao)
                        .cartao("4444555566667777")
                        .descricao(DescricaoResponseDTO
                                .builder()
                                .nsu(123L)
                                .status(com.toolsjavachallenge.dto.response.enums.StatusResponse.CANCELADO)
                                .build())
                        .build())
                .build();

        when(service.estornarPagamento(idTransacao)).thenReturn(responseDTO);

        mockMvc.perform(get("/pagamentos/" + idTransacao + "/estorno")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value(idTransacao.intValue()))
                .andExpect(jsonPath("$.transacao.descricao.status").value("CANCELADO"));
    }
}