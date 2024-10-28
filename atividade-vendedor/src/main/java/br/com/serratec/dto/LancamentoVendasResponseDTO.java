package br.com.serratec.dto;

import java.time.LocalDate;

import br.com.serratec.entity.LancamentoVendas;

public class LancamentoVendasResponseDTO {
	//atributos
	private LocalDate data;
	private Double valor;
	private String nomeVendedor;
	
	
	//construtores
	public LancamentoVendasResponseDTO() {}
	
	public LancamentoVendasResponseDTO(LancamentoVendas lancamentoVendas) {
		this.data = lancamentoVendas.getData();
		this.valor = lancamentoVendas.getValor();
		this.nomeVendedor = lancamentoVendas.getVendedorAutonomo().getNome();
	}
	
    public LancamentoVendasResponseDTO(LocalDate data, Double valor, String nomeVendedor) {
        this.data = data;
        this.valor = valor;
        this.nomeVendedor = nomeVendedor;
    }
    
	//gets sets
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

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
}

