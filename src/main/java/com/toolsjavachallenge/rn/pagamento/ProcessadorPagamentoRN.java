package com.toolsjavachallenge.rn.pagamento;

import com.toolsjavachallenge.dto.request.TransacaoRequestDTO;
import com.toolsjavachallenge.dto.request.enums.TipoPagamentoRequest;

public interface ProcessadorPagamentoRN {
    boolean suporta(TipoPagamentoRequest tipo);
    void processar(TransacaoRequestDTO transacao);
}
