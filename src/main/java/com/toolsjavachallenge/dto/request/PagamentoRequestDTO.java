package com.toolsjavachallenge.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagamentoRequestDTO {

    @NotNull(message = "A transação não pode ser nula")
    @Valid
    private TransacaoRequestDTO transacao;
}
