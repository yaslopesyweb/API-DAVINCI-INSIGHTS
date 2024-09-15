package br.com.fiap.insights.dto.opiniao;

import br.com.fiap.insights.model.Opiniao;

public record DetalhesOpiniao(Long id,String comentario) {
    public DetalhesOpiniao(Opiniao opiniao){
        this(opiniao.getId(), opiniao.getComentario());
    }
}
