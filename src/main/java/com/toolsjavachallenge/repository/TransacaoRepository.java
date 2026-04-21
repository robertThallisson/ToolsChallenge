package com.toolsjavachallenge.repository;

import com.toolsjavachallenge.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
