package br.com.fiap.insights.dto.opiniao;

import br.com.fiap.insights.model.Opiniao;

public record ListagemOpiniao(Long id,String comentario) {
    public ListagemOpiniao(Opiniao opiniao){
        this(opiniao.getId(), opiniao.getComentario());
    }

}
