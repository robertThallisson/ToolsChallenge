package com.toolsjavachallenge.entity.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Status {
    AUTORIZADO("AUTORIZADO"),
    NEGADO("NEGADO"),
    CANCELADO("CANCELADO");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() { return value; }
}
