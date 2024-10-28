package br.com.serratec.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class VendedorAutonomo extends Vendedor{
	//atributos
	@Column(nullable = false, length = 50)
	@Schema(description="Salário do usuário")
	private Double comissao;

	//getset
	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}
	
	
	
	
	
	/*
	@JsonManagedReference //essa anotação evita loopings
	@OneToMany(mappedBy = "vendedorAutonomo") // relação 1 para n
	private List<LancamentoVendas> lancamentos = new ArrayList<>();

	//gets sets
	public List<LancamentoVendas> getLancamentoVenda() {
		return lancamentos;
	}	
	
	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public List<LancamentoVendas> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoVendas> lancamentos) {
		this.lancamentos = lancamentos;
	}
	*/
	
	
}
