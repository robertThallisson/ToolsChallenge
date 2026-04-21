package com.toolsjavachallenge.entity;


import com.toolsjavachallenge.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DESCRICAO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Descricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NSU")
    private Long nsu;

    @Column(name = "VALOR")
    private BigDecimal valor;


    @Column(name = "DATA_HORA")
    private LocalDateTime dataHora;

    @Column(name = "ESTABELECIMENTO")
    private String estabelecimento;

    @Column(name = "CODIGO_AUTORIZACAO", unique = true)
    private Long codigoAutorizacao;

    @Column(name = "STATUS")
    private Status status;
}
