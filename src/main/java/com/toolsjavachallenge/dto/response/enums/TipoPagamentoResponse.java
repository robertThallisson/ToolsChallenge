package com.toolsjavachallenge.dto.response.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TipoPagamentoResponse {

    AVISTA("VISTA"),
    PARCELADO_LOJA("PARCELADO_LOJA"),
    PARCELADO_EMISSOR("PARCELADO_EMISSOR");

    private final String value;

    TipoPagamentoResponse(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() { return value; }
}
