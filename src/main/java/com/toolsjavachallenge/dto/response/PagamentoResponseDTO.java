package com.toolsjavachallenge.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagamentoResponseDTO {


    private TransacaoResponseDTO transacao;
}
