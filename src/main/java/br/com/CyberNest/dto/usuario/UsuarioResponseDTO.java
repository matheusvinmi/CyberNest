package br.com.CyberNest.dto.usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(UUID idUsuario,  String nomeUsuario, String emailUsuario,
                                 String senhaUsuario,  LocalDateTime dataCriaca) {
}
