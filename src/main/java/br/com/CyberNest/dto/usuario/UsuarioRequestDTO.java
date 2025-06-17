package br.com.CyberNest.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank(message = "O nome é um campo obrigatorio") String nomeUsuario,
        @NotBlank(message = "O email é um campo obrigatorio") String emailUsuario,
        @NotBlank(message = "A senha é um campo obrigatorio") String senhaUsuario
) {
}
