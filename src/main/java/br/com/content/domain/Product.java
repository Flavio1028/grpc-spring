package br.com.content.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Double price;

	private Integer quantityInStock;

	public Product() {
	}

	public Product(Long id, String nome, Double price, Integer quantityInStock) {
		this.id = id;
		this.nome = nome;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantityInStock() {
		return quantityInStock;
	}

}