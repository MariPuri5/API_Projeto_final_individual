package br.com.serratec.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_vendedor") //isso aqui é o DTYPE
@Entity
public abstract class Vendedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="Identificador único do usuário")
	private Long id;
	
	@NotBlank(message = "Preencha o nome")
	@Size(max = 50)
	@Column(nullable = false, length = 50)
	@Schema(description="Nome do usuário")
	private String nome;
	
	@Email
	@Schema(description="Email único do usuário")
	private String email;
	
	public class SalarioConstants {
		public static final long SALARIO_MINIMO = 1412;
	}
	
	@NotBlank(message = "Preencha o valor do salário")
	@Min(value = SalarioConstants.SALARIO_MINIMO,
	message = "O salário não pode ser menor que o salário mínimo")
	@Column(nullable = false, length = 50)
	@Schema(description="Salário do usuário")
	private Double salario;

	//getters setters
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the salario
	 */
	public Double getSalario() {
		return salario;
	}

	/**
	 * @param salario the salario to set
	 */
	public void setSalario(Double salario) {
		this.salario = salario;
	}

	
}
