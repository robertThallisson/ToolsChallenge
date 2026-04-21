package com.toolsjavachallenge.dto.response.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum StatusResponse {

    AUTORIZADO("AUTORIZADO"),
    NEGADO("NEGADO"),
    CANCELADO("CANCELADO");

    private final String value;

    StatusResponse(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() { return value; }
}
