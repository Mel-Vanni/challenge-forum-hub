package com.forumhub.controller;

import com.forumhub.dto.TopicoDTO;
import com.forumhub.model.Topico;
import com.forumhub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;

    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @PostMapping
    public ResponseEntity<Topico> criar(@RequestBody @Valid TopicoDTO dto) {
        Topico topico = new Topico();
        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());
        topico.setAutor(dto.getAutor());
        topico.setCurso(dto.getCurso());
        topicoRepository.save(topico);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public List<Topico> listar() {
        return topicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscar(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoDTO dto) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    topico.setTitulo(dto.getTitulo());
                    topico.setMensagem(dto.getMensagem());
                    topico.setAutor(dto.getAutor());
                    topico.setCurso(dto.getCurso());
                    topicoRepository.save(topico);
                    return ResponseEntity.ok(topico);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    topicoRepository.delete(topico);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}