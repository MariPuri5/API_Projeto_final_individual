package br.com.serratec.dto;

import java.time.LocalDate;

import br.com.serratec.entity.LancamentoVendas;
//não implementado
public class LancamentoVendasSemNomeResponseDTO {
	//atributos
	private LocalDate data;
	private Double valor;

    
    //sem nome
    public LancamentoVendasSemNomeResponseDTO() {}
    
    public LancamentoVendasSemNomeResponseDTO(LancamentoVendas lancamentoVendas) {
    	this.data = lancamentoVendas.getData();
    	this.valor = lancamentoVendas.getValor();
    }
    
    public LancamentoVendasSemNomeResponseDTO(LocalDate data, Double valor, String nomeVendedor) {
    	this.data = data;
    	this.valor = valor;
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
}

