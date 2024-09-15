package br.com.fiap.insights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.insights.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
