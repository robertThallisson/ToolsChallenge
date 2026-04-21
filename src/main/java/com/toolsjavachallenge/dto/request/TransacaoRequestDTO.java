package com.toolsjavachallenge.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransacaoRequestDTO {



    @NotBlank(message = "Cartão é obrigatório")
    private String cartao;

    @NotNull(message = "ID da transação é obrigatório")
    private Long id;

    @NotNull(message = "Descrição é obrigatória")
    @Valid
    private DescricaoRequestDTO descricao;

    @NotNull(message = "Forma de pagamento é obrigatória")
    @Valid
    private FormaPagamentoRequestDTO formaPagamento;
}
