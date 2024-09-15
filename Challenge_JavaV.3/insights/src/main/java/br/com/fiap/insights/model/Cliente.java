package br.com.fiap.insights.model;

import br.com.fiap.insights.dto.cliente.AtualizarCliente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="CLIENTE")
public class Cliente  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cliente_id")
    private Long id;

    @Column(name="nome")
    private String nome;

    @Column(name="login")
    private String login;

    @Column(name="senha", nullable = false)
    private String password;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Opiniao> opiniaoList;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Produto> produtoList;


    public void atualizarCliente(AtualizarCliente dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.login() != null)
            login = dto.login();
        if (dto.password() != null)
            password = dto.password();

    }
}