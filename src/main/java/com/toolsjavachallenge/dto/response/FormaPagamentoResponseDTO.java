package com.toolsjavachallenge.dto.response;

import com.toolsjavachallenge.dto.response.enums.TipoPagamentoResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FormaPagamentoResponseDTO {

    @Enumerated(EnumType.STRING)
    private TipoPagamentoResponse tipo;

    private Integer parcela;
}
