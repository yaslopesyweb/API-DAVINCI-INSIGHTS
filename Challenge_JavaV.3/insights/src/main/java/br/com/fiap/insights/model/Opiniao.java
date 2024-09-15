package br.com.fiap.insights.model;

import br.com.fiap.insights.dto.opiniao.AtualizarOpiniao;
import br.com.fiap.insights.dto.opiniao.CadastrarOpiniao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="OPINIAO")
public class Opiniao {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="opiniao_id")
    private Long id;

    @Column(name="comentario")
    private String comentario;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    public Opiniao(CadastrarOpiniao opiniaoDto){
        comentario = opiniaoDto.comentario();
    }
    public void atualizarOpiniao(AtualizarOpiniao dto){
        if (dto.comentario() !=null)
            comentario = dto.comentario();
    }
    
}
