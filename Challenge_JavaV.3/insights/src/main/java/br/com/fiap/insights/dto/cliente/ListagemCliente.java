package br.com.fiap.insights.dto.cliente;

import br.com.fiap.insights.model.Cliente;


public record ListagemCliente(Long id,String nome ,String login, String password) {
    public  ListagemCliente(Cliente cliente){this(cliente.getId(),cliente.getNome(),cliente.getLogin(),cliente.getPassword());}
}
