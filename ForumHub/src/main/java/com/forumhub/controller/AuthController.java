package com.forumhub.controller;

import com.forumhub.dto.LoginDTO;
import com.forumhub.model.Usuario;
import com.forumhub.repository.UsuarioRepository;
import com.forumhub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody @Valid Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginDTO dto) {
        Usuario usuario = usuarioRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {
            return ResponseEntity.badRequest().body("Senha inválida");
        }

        String token = tokenService.gerarToken(usuario);
        return ResponseEntity.ok(token);
    }
}