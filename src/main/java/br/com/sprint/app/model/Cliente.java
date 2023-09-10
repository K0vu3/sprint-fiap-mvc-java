package br.com.sprint.app.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Cliente {
    private Long id;
    private String cnpj;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date date;

    public Cliente(Long id, String cnpj, Date date) {
        this.id = id;
        this.cnpj = cnpj;
        this.date = date;
    }

    public Cliente() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

}
