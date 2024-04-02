package com.sicred.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TipoPagamento {

    AVISTA(0, "AVISTA"),
    PARCELADO_LOJA(1, "PARCELADO LOJA"),
    PARCELADO_EMISSOR(2, "PARCELADO EMISSOR");

    Integer value;
    String nome;
    TipoPagamento(final Integer value, final String nome) {
        this.value = value;
        this.nome = nome;
    }

    Integer get() {
        return value;
    }
    public TipoPagamento get(Integer value) {
        for (TipoPagamento tipoPagamento: values()) {
            if (tipoPagamento.get().equals(value)) {
                return  tipoPagamento;
            }
        }
        return get(0);
    }
}
