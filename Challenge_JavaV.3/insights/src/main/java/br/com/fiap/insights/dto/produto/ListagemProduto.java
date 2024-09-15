package br.com.fiap.insights.dto.produto;

import br.com.fiap.insights.model.Produto;

import java.math.BigDecimal;

public record ListagemProduto(Long id,String nome, BigDecimal valor) {

    public ListagemProduto(Produto produto){

        this(produto.getId(),produto.getNome(), produto.getValor());
    }
}
