package com.toolsjavachallenge.rn.pagamento;

import com.toolsjavachallenge.dto.request.TransacaoRequestDTO;
import com.toolsjavachallenge.dto.request.enums.TipoPagamentoRequest;
import com.toolsjavachallenge.exception.ValidacaoPagamentoException;

public class ProcessadorPagamentoAVistaRN implements ProcessadorPagamentoRN{
    private final Integer NUMERO_MAXIMO_PARCELA_AVISTA = 1;

    @Override
    public boolean suporta(TipoPagamentoRequest tipo) {
        return tipo == TipoPagamentoRequest.AVISTA;
    }

    @Override
    public void processar(TransacaoRequestDTO transacao) {
        if (NUMERO_MAXIMO_PARCELA_AVISTA.equals(transacao.getFormaPagamento().getParcela())) {
            throw new ValidacaoPagamentoException("Pagamento à vista deve ter apenas 1 parcela.");
        }
    }
}
