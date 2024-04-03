package com.sicred.service.transacao;

import com.sicred.exceptionhandler.ToolsChallengeException;
import com.sicred.model.Transacao;
import com.sicred.model.enums.StatusTrancacao;
import com.sicred.repository.DescricaoRepository;
import com.sicred.repository.TransacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.Random;

@RestController
@RequestMapping("/transacao")
public class TransacaoService  {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    DescricaoRepository descricaoRepository;

        @PostMapping("/autorizar")
    public @ResponseBody ResponseEntity<Transacao> autorizar(@RequestBody @Valid  Transacao transacao) {

        if (transacaoRepository.findById(transacao.getId()).isPresent()) {
            throw new ToolsChallengeException("Não e permitido pagamentos com mesmo id", "Não e permitido pagamentos com mesmo id, erro de integridade com bd");
        }

        if (transacao.getDescricao().getValor().compareTo(new BigDecimal("500.50")) != 0) {
            transacao.getDescricao().setStatus(StatusTrancacao.NEGADO);
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(transacao);
        }
        transacao.getDescricao().setCodigoAutorzacao(new Random().nextLong());
        transacao.getDescricao().setStatus(StatusTrancacao.AUTORIZADO);

        descricaoRepository.save(transacao.getDescricao());
        transacaoRepository.save(transacao);
        return ResponseEntity.ok(transacao);
    }


    @PutMapping("/estornar")
    public @ResponseBody ResponseEntity<Transacao> estornar(@RequestBody @Valid Transacao transacao) {
        if (transacaoRepository.findById(transacao.getId()).isEmpty()) {
            throw new ToolsChallengeException("Transação inexistente", "Transação não existe na base de dados ");
        }
        transacao.getDescricao().setStatus(StatusTrancacao.CANCELADO);
        descricaoRepository.save(transacao.getDescricao());
        return ResponseEntity.ok(transacao);

    }


}
