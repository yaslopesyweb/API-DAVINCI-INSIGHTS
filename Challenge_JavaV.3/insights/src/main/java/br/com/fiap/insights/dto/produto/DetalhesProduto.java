package br.com.fiap.insights.dto.produto;

import br.com.fiap.insights.model.Produto;

import java.math.BigDecimal;

public record DetalhesProduto(Long id, String nome, BigDecimal valor) {

    public DetalhesProduto(Produto produto){

        this(produto.getId(),produto.getNome(), produto.getValor());
    }
}

