package br.com.fiap.insights.model;

import java.math.BigDecimal;

import br.com.fiap.insights.dto.produto.AtualizarProduto;
import br.com.fiap.insights.dto.produto.CadastrarProduto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter@Setter
@NoArgsConstructor
@Table(name="PRODUTO")
public class Produto {

    @Id
    @GeneratedValue
    @Column(name="produto_id")
    private Long id;


    @Column(name="nome")
    private String nome;

    @Column(name="valor")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    public Produto(CadastrarProduto dto){
        nome = dto.nome();
        valor = dto.valor();

    }


    public void atualizarProduto(AtualizarProduto dto){
        if (dto.nome() !=null)
            nome = dto.nome();
        if (dto.valor() !=null)
            valor = dto.valor();
    }

}