package com.toolsjavachallenge.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransacaoResponseDTO {
    private String cartao;

    private Long id;

    private DescricaoResponseDTO descricao;

    private FormaPagamentoResponseDTO formaPagamento;
}
