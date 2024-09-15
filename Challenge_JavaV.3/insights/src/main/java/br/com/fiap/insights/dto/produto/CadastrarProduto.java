package br.com.fiap.insights.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CadastrarProduto(

        @NotBlank
        @Size(max=80)
        String nome,

        @NotNull
        BigDecimal valor) {
}
