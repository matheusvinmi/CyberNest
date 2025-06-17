package br.com.CyberNest.repository;

import br.com.CyberNest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmailUsuario(String emailUsuario);
    boolean existsByEmailUsuario(String emailUsuario);
}
