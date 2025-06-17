package br.com.CyberNest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email_usuario"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario")
    private UUID idUsuario;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "email_usuario", nullable = false)
    private String emailUsuario;

    @Column(name = "senha_usuario", nullable = false)
    private String senhaUsuario;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao = LocalDateTime.now();

    @PreUpdate
    public void aoAtualizar() {
        this.ultimaAtualizacao = LocalDateTime.now();
    }
}
