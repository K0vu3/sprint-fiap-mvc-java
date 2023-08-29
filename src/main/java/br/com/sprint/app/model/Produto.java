package br.com.sprint.app.model;

import java.util.Date;

public class Produto {

    public Produto(Long id, String nome, Date date) {
        this.id = id;
        this.nome = nome;
        this.date = date;
    }

    private Long id;
    private String nome;
    private Date date;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
