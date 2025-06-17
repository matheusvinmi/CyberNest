package br.com.CyberNest.service;


import br.com.CyberNest.dto.usuario.UsuarioRequestDTO;
import br.com.CyberNest.dto.usuario.UsuarioResponseDTO;
import br.com.CyberNest.model.Usuario;
import br.com.CyberNest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> listarTodos(){
        return usuarioRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(UUID idUsuario){
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        return toResponse(usuario);
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO request){
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(request.nomeUsuario());
        usuario.setEmailUsuario(request.emailUsuario());
        usuario.setSenhaUsuario(passwordEncoder.encode(request.senhaUsuario()));
        Usuario salvo = usuarioRepository.save(usuario);
        return toResponse(salvo);
    }

    public UsuarioResponseDTO atualizarUsuario(UUID idUsuario, UsuarioRequestDTO request){
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        usuario.setNomeUsuario(request.nomeUsuario());
        usuario.setEmailUsuario(request.emailUsuario());
        usuario.setSenhaUsuario(request.senhaUsuario());

        Usuario atualizado = usuarioRepository.save(usuario);
        return toResponse(atualizado);
    }

    public void deletarUsuario(UUID idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }



        private UsuarioResponseDTO toResponse(Usuario usuario){
            return new UsuarioResponseDTO(
                    usuario.getIdUsuario(),
                    usuario.getNomeUsuario(),
                    usuario.getEmailUsuario(),
                    usuario.getSenhaUsuario(),
                    usuario.getDataCriacao()
            );
        }

}
