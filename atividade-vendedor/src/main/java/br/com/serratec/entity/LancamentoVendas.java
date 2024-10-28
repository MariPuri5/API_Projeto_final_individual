package br.com.serratec.entity;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class LancamentoVendas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(description = "Identificador único da venda")
	private Long id;

	@Column(nullable = false, length = 50)
	@Schema(description = "Preencha a data da venda AAAA-MM-DD")
	private LocalDate data; // 2024-10-12 21:57

	@NotNull
	@Positive
	@Schema(description = "Valor do produto vendido")
	private Double valor;

	@ManyToOne // Relação muitos para um com VendedorAutonomo
	@JoinColumn(name = "vendedor_autonomo_id")
	private VendedorAutonomo vendedorAutonomo;

	//gets sets
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public VendedorAutonomo getVendedorAutonomo() {
		return vendedorAutonomo;
	}

	public void setVendedorAutonomo(VendedorAutonomo vendedorAutonomo) {
		this.vendedorAutonomo = vendedorAutonomo;
	}
}



/*
 * //relação ORM com vendedor autonomo n vendas para 1 vendedor
 * 
 * //Foreign Key //LANÇAMENTOS DE VENDA DO VENDEDOR AUTONOMO
 * 
 * @ManyToOne //relação n para 1
 * 
 * @JsonBackReference //complementa o @JsonManagedReference para não dar loops
 * 
 * @JoinColumn(name ="id_vendedorAutonomo") private VendedorAutonomo
 * vendedorAutonomo;
 * 
 * public Long getId() { return id; }
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * public LocalDate getData() { return data; }
 * 
 * public void setData(LocalDate data) { this.data = data; }
 * 
 * public Double getValor() { return valor; }
 * 
 * public void setValor(Double valor) { this.valor = valor; }
 * 
 * public VendedorAutonomo getVendedorAutonomo() { return vendedorAutonomo; }
 * 
 * public void setVendedorAutonomo(VendedorAutonomo vendedorAutonomo) {
 * this.vendedorAutonomo = vendedorAutonomo; } }
 */