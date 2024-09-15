package br.com.fiap.insights.dto.autenticacao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO (
        String nome,
        @Email
        String login,
        @NotBlank
        @NotNull
        String password) {


}
