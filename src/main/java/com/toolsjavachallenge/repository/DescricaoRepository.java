package com.toolsjavachallenge.repository;

import com.toolsjavachallenge.entity.Descricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescricaoRepository extends JpaRepository<Descricao, Long> {
}
