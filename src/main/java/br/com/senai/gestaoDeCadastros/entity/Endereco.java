package br.com.senai.gestaoDeCadastros.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Endereco")
@Table(name = "enderecos")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

	@Id
	@Column(name = "id")
	@NotNull(message = "O id é obrigatório. ")
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "nome")
	@NotBlank(message = "O nome é obrigatório. ")
	private String nome;
	
	@Column(name = "cep")
	@NotBlank(message = "O cep é obrigatório. ")
	private String cep;
	
	@Column(name = "rua")
	@NotBlank(message = "A rua é obrigatória. ")
	private String rua;
	
	@Column(name = "cidade")
	@NotBlank(message = "A cidade é obrigatória. ")
	private String cidade;
	
	@Column(name = "estado")
	@NotBlank(message = "O estado é obrigatório. ")
	private String estado;
	
	@Column(name = "numero_casa")
	@NotBlank(message = "O numero da casa é obrigatório. ")
	private String numeroDaCasa;
	
	@Column(name = "complemento")
	private String complemento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	@NotNull(message = "O cliente é obrigatório. ")
	private Cliente cliente;
	
}
