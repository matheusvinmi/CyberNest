package br.com.CyberNest.controller;

import br.com.CyberNest.dto.login.AuthenticationDTO;
import br.com.CyberNest.dto.login.LoginResponseDTO;
import br.com.CyberNest.dto.usuario.UsuarioRequestDTO;
import br.com.CyberNest.model.Usuario;
import br.com.CyberNest.repository.UsuarioRepository;
import br.com.CyberNest.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.emailUsuario(), authenticationDTO.senhaUsuario());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO){
        if (this.usuarioRepository.findByEmailUsuario(usuarioRequestDTO.emailUsuario()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRequestDTO.senhaUsuario());
        Usuario usuario = new Usuario(usuarioRequestDTO.emailUsuario(), encryptedPassword, usuarioRequestDTO.role());
        this.usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}
