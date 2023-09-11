package br.com.sprint.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produtox")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String data;

	@ManyToOne
    @JoinColumn(name = "cliente_id") // Nome da coluna de chave estrangeira na tabela Produto
    private Cliente cliente;

	

	public Produto(Long id, String nome, String data, Cliente cliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.cliente = cliente;
	}

	public Produto() {
	}

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

	public void setDate(String date) {
		this.data = date;
	}

	public String getDate() {
		return data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
