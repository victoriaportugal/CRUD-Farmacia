package com.generation.farmacia.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="NOME DO PRODUTO É OBRIGATÓRIO")
	@Size(min=5 , message="O TAMANHO MINIMO DO NOME PARA O PRODUTO É DE 5 CARACTERES")
	private String nome;
	@NotBlank(message="DESCRICAO DO PRODUTO É OBRIGATÓRIA")
	@Size(min=5 , message="O TAMANHO MINIMO DA DESCRICAO PARA O PRODUTO É DE 5 CARACTERES")
	private String descricao;
	@NotNull(message = "O preço do produto é obrigatório.")
	@Column(precision = 8, scale = 2) // = DECIMAL(8,2)
	private BigDecimal valor;
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;
	
	
	public Produto() {
		super();
	}
	
	public Produto(Long id,
			@NotBlank(message = "NOME DO PRODUTO É OBRIGATÓRIO") @Size(min = 5, message = "O TAMANHO MINIMO DO NOME PARA O PRODUTO É DE 5 CARACTERES") String nome,
			@NotBlank(message = "DESCRICAO DO PRODUTO É OBRIGATÓRIA") @Size(min = 5, message = "O TAMANHO MINIMO DA DESCRICAO PARA O PRODUTO É DE 5 CARACTERES") String descricao,
			@NotNull(message = "O preço do produto é obrigatório.") BigDecimal valor, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
	
}
