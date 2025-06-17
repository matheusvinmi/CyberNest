package br.com.CyberNest.controller;

import br.com.CyberNest.dto.usuario.UsuarioRequestDTO;
import br.com.CyberNest.dto.usuario.UsuarioResponseDTO;
import br.com.CyberNest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable UUID id){
       UsuarioResponseDTO usuarioResponseDTO = usuarioService.buscarPorId(id);
       return  ResponseEntity.ok(usuarioResponseDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioRequestDTO requestDTO){
        UsuarioResponseDTO criado = usuarioService.criarUsuario(requestDTO);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable UUID id, @RequestBody UsuarioRequestDTO requestDTO){
        UsuarioResponseDTO atualizado = usuarioService.atualizarUsuario(id, requestDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
