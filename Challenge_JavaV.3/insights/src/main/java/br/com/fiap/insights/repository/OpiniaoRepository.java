package br.com.fiap.insights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.insights.model.Opiniao;

public interface OpiniaoRepository extends JpaRepository<Opiniao, Long>{
    
}
