package br.com.senai.gestaoDeCadastros.entity;

import java.sql.Date;

import br.com.senai.gestaoDeCadastros.entity.enums.Sexo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Cliente")
@Table(name = "clientes")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
	
	@Id
	@Column(name = "id")
	@Positive
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "nome")
	@NotBlank(message = "O nome é obrigatório. ")
	private String nome;
	
	@Column(name = "sexo")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O sexo do cliente é obrigatório. ")
	private Sexo sexo;
	
	@Column(name = "data_nascimento")
	@NotNull(message = "A data de nascimento é obrigatória. ")
	private Date dataDeNascimento;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	private Usuario usuario;
	
}

