package com.toolsjavachallenge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TRANSACAO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @Column(name = "TRANSACAO_ID", unique = true)
    private Long id;

    @Column(name = "CARTAO")
    private String cartao;
    @Embedded
    private FormaPagamento formaPagamento;

    @OneToOne
    @JoinColumn(name = "DESCRICAO_NSU", referencedColumnName = "NSU")
    private Descricao  descricao;

}
