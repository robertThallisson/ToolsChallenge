package com.sicred.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Transacao  {

    @Id
    @Column(unique=true)
    @NotNull(message = "id não pode ser nulo")
    Long id;
    @NotBlank(message = "cartão não pode ser nulo")
    String cartao;

    @OneToOne
    @NotNull(message = "descrição não pode ser nulo")
    Descricao descricao;
    @NotNull(message = "forma de pagamento não pode ser nulo")
    FormaPagamento formaPagamento;
}
