package com.toolsjavachallenge.controller;


import com.toolsjavachallenge.dto.request.PagamentoRequestDTO;
import com.toolsjavachallenge.dto.response.PagamentoResponseDTO;
import com.toolsjavachallenge.service.PagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@Tag( name = "Controle de pagamentos",description = "Permite gerir pagamentos")
public class PagamentoCtrl {
    private final PagamentoService service;

    public PagamentoCtrl(PagamentoService service) {
        this.service = service;
    }

    @PostMapping("/autorizar")
    @Tag(name = "criar pagamentos", description = "Permmite criar um novo pagamento com base no payload")
    public ResponseEntity<PagamentoResponseDTO> pagar(@Valid @RequestBody PagamentoRequestDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.processarPagamento(payload));
    }

    @GetMapping("/{id}/estorno")
    @Tag(name = "Estornar pagamentos", description = "Permiter estorna um pagamento ja existene")
    public ResponseEntity<PagamentoResponseDTO> estornar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.estornarPagamento(id));
    }

    @GetMapping("/buscar/{id}")
    @Tag(name = "Buscar Por Id",description = "Retordar das de um pagamentos atraves de seu id")
    public ResponseEntity<PagamentoResponseDTO> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }


    @GetMapping("/listartodos")
    @Tag(name = "listar todos ", description = "Retorna todos os pagamentos existentes ")
    public ResponseEntity<List<PagamentoResponseDTO>> consultarTodos() {
        return ResponseEntity.ok(service.buscarTodas());
    }
}
