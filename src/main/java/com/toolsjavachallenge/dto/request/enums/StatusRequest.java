package com.toolsjavachallenge.dto.request.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum StatusRequest {
    AUTORIZADO("AUTORIZADO"),
    NEGADO("NEGADO"),
    CANCELADO("CANCELADO");

    private final String value;

    StatusRequest(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() { return value; }
}
