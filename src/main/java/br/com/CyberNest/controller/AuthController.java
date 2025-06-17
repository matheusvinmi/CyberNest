package br.com.CyberNest.controller;

import br.com.CyberNest.dto.login.LoginRequestDTO;
import br.com.CyberNest.model.Usuario;
import br.com.CyberNest.repository.UsuarioRepository;
import br.com.CyberNest.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO){
        Usuario usuario = usuarioRepository.findByEmailUsuario(loginRequestDTO.emailUsuario())
                .orElse(null);

        if(usuario == null || !passwordEncoder.matches(loginRequestDTO.senhaUsuario(), usuario.getSenhaUsuario())){
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        String token = jwtUtil.gerarToken(usuario.getIdUsuario(), usuario.getEmailUsuario());
        return ResponseEntity.ok().body(Map.of("token", token));
    }
}
