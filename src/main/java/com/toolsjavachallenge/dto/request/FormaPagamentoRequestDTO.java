package com.toolsjavachallenge.dto.request;

import com.toolsjavachallenge.dto.request.enums.TipoPagamentoRequest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FormaPagamentoRequestDTO {

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tipo de pagamento é obrigatório")
    private TipoPagamentoRequest tipo;

    @NotBlank(message = "Quantidade de parcelas é obrigatória")
    private Integer parcela;
}
