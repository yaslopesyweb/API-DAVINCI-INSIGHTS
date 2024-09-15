package br.com.fiap.insights.dto.opiniao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastrarOpiniao(

        @NotBlank
        @Size(max=200)
        String comentario) {
}
