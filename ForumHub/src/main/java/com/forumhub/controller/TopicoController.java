package com.forumhub.controller;

import com.forumhub.model.Topico;
import com.forumhub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity<?> criarTopico(@Valid @RequestBody Topico topico){
        if(repository.existsByTituloAndMensagem(topico.getTitulo(), topico.getMensagem()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tópico já existe");
        repository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @GetMapping
    public List<Topico> listarTopicos(){ return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> detalharTopico(@PathVariable Long id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTopico(@PathVariable Long id, @Valid @RequestBody Topico dados){
        return repository.findById(id)
                .map(topico -> {
                    topico.setTitulo(dados.getTitulo());
                    topico.setMensagem(dados.getMensagem());
                    topico.setAutor(dados.getAutor());
                    topico.setCurso(dados.getCurso());
                    topico.setStatus(dados.getStatus());
                    repository.save(topico);
                    return ResponseEntity.ok(topico);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id){
        if(!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
