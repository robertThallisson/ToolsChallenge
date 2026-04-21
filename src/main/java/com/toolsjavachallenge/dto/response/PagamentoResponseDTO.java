package com.toolsjavachallenge.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoResponseDTO {


    private TransacaoResponseDTO transacao;
}
