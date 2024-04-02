package com.sicred.model;

import com.sicred.model.enums.TipoPagamento;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class FormaPagamento {

    @NotNull(message = "tipo pagamento não pode ser nulo")
    TipoPagamento tipoPagamento;

    @NotNull(message = "parcela não pode ser nulo")
    Integer parcela;
}
