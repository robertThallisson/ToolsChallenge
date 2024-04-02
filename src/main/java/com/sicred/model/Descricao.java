package com.sicred.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sicred.model.enums.StatusTrancacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Descricao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long nsu;

    @NotNull(message = "valor não pode ser nulo")
    BigDecimal valor;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull(message = "data não pode ser nulo")
    LocalDateTime dataHora;
    @NotBlank(message = "estabelecimento não pode ser nulo")
    String estabelecimento;

    Long codigoAutorzacao;
    StatusTrancacao status;
}
