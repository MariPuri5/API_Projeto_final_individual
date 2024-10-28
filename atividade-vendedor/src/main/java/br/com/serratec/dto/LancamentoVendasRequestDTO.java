package br.com.serratec.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.serratec.entity.LancamentoVendas;
import br.com.serratec.entity.VendedorAutonomo;
//não implementado
public class LancamentoVendasRequestDTO {	
	//atributos
	private LocalDate data;
	private Double valor;
	private String nomeVendedor;
	

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	private VendedorAutonomo vendedorAutonomo;
	public VendedorAutonomo getVendedorAutonomo() {
		return vendedorAutonomo;
	}
	public void setVendedorAutonomo(VendedorAutonomo vendedorAutonomo) {
		this.vendedorAutonomo = vendedorAutonomo;
	}

	private Set<LancamentoVendas> lancamentoVendas = new HashSet<>();
	//getset
	public Set<LancamentoVendas> getLancamentoVendas() {
		return lancamentoVendas;
	}	
	public void setLancamentoVendas(Set<LancamentoVendas> lancamentoVendas) {
		this.lancamentoVendas = lancamentoVendas;
	}

	//Construtor pedindo Lancamento lancamento e usando os gets
	public LancamentoVendasRequestDTO(LancamentoVendas lancamentoVendas) {
		this.data = lancamentoVendas.getData();
		this.valor = lancamentoVendas.getValor();
		this.vendedorAutonomo.setNome(lancamentoVendas.getVendedorAutonomo().getNome());
		//this.nomeVendedor = lancamentoVendas.getVendedorAutonomo().getNome();
	}
	//Construtor vazio
	public LancamentoVendasRequestDTO() {
	}
	//gets 
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
