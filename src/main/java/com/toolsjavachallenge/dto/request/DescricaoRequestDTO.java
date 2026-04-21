package com.toolsjavachallenge.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.toolsjavachallenge.dto.request.enums.StatusRequest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class DescricaoRequestDTO {


    private Long nsu;

    @NotBlank(message = "Valor é obrigatório")
    private BigDecimal valor;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotBlank(message = "Data e Hora são obrigatórios")
    private LocalDateTime dataHora;

    @NotBlank(message = "Estabelecimento é obrigatório")
    private String estabelecimento;



    private Long codigoAutorizacao;

    @Enumerated(EnumType.STRING)
    private StatusRequest status;
}
