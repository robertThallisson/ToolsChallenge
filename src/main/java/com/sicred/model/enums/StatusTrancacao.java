package com.sicred.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum StatusTrancacao {


    AUTORIZADO(0, "AUTORIZADO"),
    NEGADO(1, "NEGADO"),
    CANCELADO(2, "CANCELADO");

    Integer value;
    String nome;
    StatusTrancacao(final Integer value, final String nome) {
        this.value = value;
        this.nome = nome;
    }

    Integer get() {
        return value;
    }
    public StatusTrancacao get(Integer value) {
        for (StatusTrancacao tipoPagamento: values()) {
            if (tipoPagamento.get().equals(value)) {
                return  tipoPagamento;
            }
        }
        return get(0);
    }
}
