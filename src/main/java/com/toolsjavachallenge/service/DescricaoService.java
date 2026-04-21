package com.toolsjavachallenge.service;

import com.toolsjavachallenge.dto.request.DescricaoRequestDTO;
import com.toolsjavachallenge.entity.Descricao;
import com.toolsjavachallenge.entity.enums.Status;
import com.toolsjavachallenge.exception.ValidacaoPagamentoException;
import com.toolsjavachallenge.repository.DescricaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DescricaoService {

    private final DescricaoRepository descricaoRepository;

    public DescricaoService(DescricaoRepository descricaoRepository) {
        this.descricaoRepository = descricaoRepository;
    }

    @Transactional
    public Descricao save(DescricaoRequestDTO payload) {

        Descricao descricao = new Descricao();

        descricao.setCodigoAutorizacao(gerarCodigoAutorizacao());
        descricao.setValor(payload.getValor()) ;
        descricao.setEstabelecimento(payload.getEstabelecimento());
        descricao.setDataHora(descricao.getDataHora());

        return descricaoRepository.save(descricao);
    }

    public void estornarDescricao(Long nsu) {

        Descricao descricao = descricaoRepository.findById(nsu).get();
        descricao.setStatus(Status.CANCELADO);
        descricaoRepository.save(descricao);

    }



    private Long gerarCodigoAutorizacao() { return System.currentTimeMillis(); }
}
