package com.forumhub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String curso;

    public Topico() {}

    public Topico(String titulo, String mensagem, String autor, String curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
}