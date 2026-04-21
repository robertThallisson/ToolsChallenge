package com.toolsjavachallenge.service;

import com.toolsjavachallenge.dto.request.FormaPagamentoRequestDTO;
import com.toolsjavachallenge.dto.request.PagamentoRequestDTO;
import com.toolsjavachallenge.dto.request.TransacaoRequestDTO;
import com.toolsjavachallenge.dto.request.enums.StatusRequest;
import com.toolsjavachallenge.dto.request.enums.TipoPagamentoRequest;
import com.toolsjavachallenge.dto.response.DescricaoResponseDTO;
import com.toolsjavachallenge.dto.response.PagamentoResponseDTO;
import com.toolsjavachallenge.dto.response.TransacaoResponseDTO;
import com.toolsjavachallenge.dto.response.enums.StatusResponse;
import com.toolsjavachallenge.entity.Descricao;
import com.toolsjavachallenge.entity.FormaPagamento;
import com.toolsjavachallenge.entity.Transacao;
import com.toolsjavachallenge.entity.enums.Status;
import com.toolsjavachallenge.entity.enums.TipoPagamento;
import com.toolsjavachallenge.exception.RecursoNaoEncontradoException;
import com.toolsjavachallenge.exception.ValidacaoPagamentoException;
import com.toolsjavachallenge.repository.TransacaoRepository;
import com.toolsjavachallenge.rn.pagamento.ProcessadorPagamentoRN;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {

    private final TransacaoRepository repository;
    private final List<ProcessadorPagamentoRN> processadores;


    private final DescricaoService descricaoService;
    public PagamentoService(TransacaoRepository repository, List<ProcessadorPagamentoRN> processadores, DescricaoService descricaoService) {
        this.repository = repository;
        this.processadores = processadores;
        this.descricaoService = descricaoService;
    }

    @Transactional
    public PagamentoResponseDTO processarPagamento(PagamentoRequestDTO payload) {
        TransacaoRequestDTO transacao = payload.getTransacao();

        if(repository.findById(transacao.getId()).isPresent()) {
            throw new ValidacaoPagamentoException("Transação com ID " + transacao.getId() + " já existe.");
        }

        if (transacao.getDescricao().getNsu() != null || !transacao.getDescricao().getNsu().toString().isEmpty()) {
            new ValidacaoPagamentoException("Não e permetido informa NSU ao criar um novo pagamento");
        }
        if (transacao.getDescricao().getCodigoAutorizacao() != null ||
                !transacao.getDescricao().getCodigoAutorizacao().toString().isEmpty()) {
            new ValidacaoPagamentoException("Não e permetido informa  ao criar um novo pagamento");
        }

        ProcessadorPagamentoRN processador = processadores.stream()
                .filter(p -> p.suporta(transacao.getFormaPagamento().getTipo()))
                .findFirst()
                .orElseThrow(() -> new ValidacaoPagamentoException("Forma de pagamento não suportada"));

        processador.processar(transacao);

        Transacao transacaoEntity  = Transacao.
                builder().
                descricao(descricaoService.save(transacao.getDescricao())).
                formaPagamento(getFormaPagamento(transacao.getFormaPagamento())).
                id(transacao.getId()).
                cartao(transacao.getCartao()).
                build();



        repository.save(transacaoEntity);

        return PagamentoResponseDTO.builder()
                .transacao(getTransaoResponse(transacaoEntity))
        .build();
    }

    private TransacaoResponseDTO getTransaoResponse(Transacao transacaoEntity) {
        return TransacaoResponseDTO.builder()
                .id(transacaoEntity.getId())
                .cartao(transacaoEntity.getCartao())
                .descricao(getDescricaoResponse(transacaoEntity.getDescricao()))
                .build();
    }

    private DescricaoResponseDTO getDescricaoResponse(Descricao descricao) {
        return DescricaoResponseDTO.builder()
                .nsu(descricao.getNsu())
                .valor(descricao.getValor())
                .codigoAutorizacao(descricao.getCodigoAutorizacao())
                .dataHora(descricao.getDataHora())
                .estabelecimento(descricao.getEstabelecimento())
                .status(getStatusResponse(descricao.getStatus())).build();
    }

    private StatusResponse getStatusResponse(Status status) {
        switch (status) {
            case NEGADO -> {
                return StatusResponse.NEGADO;
            }
            case CANCELADO -> {
                return StatusResponse.CANCELADO;
            }

            default -> {
                return StatusResponse.AUTORIZADO;
            }
        }
    }

    private FormaPagamento getFormaPagamento(FormaPagamentoRequestDTO formaPagamento) {
        return FormaPagamento.builder()
                .parcelas(formaPagamento.getParcela())
                .tipoPagamento(getTipoPagamento(formaPagamento.getTipo()))
                .build();
    }

    private TipoPagamento getTipoPagamento(TipoPagamentoRequest tipo) {
        switch (tipo) {
            case PARCELADO_LOJA -> {
                return TipoPagamento.PARCELADO_LOJA;
            }

            case PARCELADO_EMISSOR -> {
                return TipoPagamento.PARCELADO_EMISSOR;
            }

            default -> {
                return TipoPagamento.AVISTA;
            }

        }
    }

    public PagamentoResponseDTO estornarPagamento(Long id) {
        Transacao transacao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Transação não encontrada"));

        if(transacao.getDescricao().getStatus() != Status.AUTORIZADO) {
            new RecursoNaoEncontradoException("So e possivel estornar transação autorizadas");
        }

        descricaoService.estornarDescricao(transacao.getDescricao().getNsu());

        transacao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Transação não encontrada"));

        return PagamentoResponseDTO.builder()
                .transacao(getTransaoResponse(transacao))
                .build();
    }

    public PagamentoResponseDTO buscarPorId(Long id) {
        Transacao transacao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Transação não encontrada"));

        return PagamentoResponseDTO.builder().transacao(getTransaoResponse(transacao)).build();
    }

    public List<PagamentoResponseDTO> buscarTodas() {
        return  repository.findAll().stream().map(
                (elemento) -> {
                    return PagamentoResponseDTO.builder().transacao(getTransaoResponse(elemento)).build();
                }

        ).toList();
    }
}
