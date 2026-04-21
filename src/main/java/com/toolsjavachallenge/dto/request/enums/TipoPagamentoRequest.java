package com.toolsjavachallenge.dto.request.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TipoPagamentoRequest {

    AVISTA("AVISTA"),
    PARCELADO_LOJA("PARCELADO_LOJA"),
    PARCELADO_EMISSOR("PARCELADO_EMISSOR");

    private final String value;

    TipoPagamentoRequest(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() { return value; }
}
