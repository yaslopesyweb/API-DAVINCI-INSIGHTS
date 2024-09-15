package br.com.fiap.insights.dto.cliente;

import br.com.fiap.insights.model.Cliente;

public record DetalhesCliente(Long id,String nome ,String login, String password) {
    public  DetalhesCliente(Cliente cliente){this(cliente.getId(),cliente.getNome(),cliente.getLogin(),cliente.getPassword());}
}
