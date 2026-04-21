package com.toolsjavachallenge.dto.response;

import com.toolsjavachallenge.dto.response.enums.TipoPagamentoResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FormaPagamentoResponseDTO {

    @Enumerated(EnumType.STRING)
    private TipoPagamentoResponse tipo;

    private Integer parcelas;
}
