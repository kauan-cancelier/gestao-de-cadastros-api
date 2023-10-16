package br.com.senai.gestaoDeCadastros.entity;

import java.sql.Timestamp;

import br.com.senai.gestaoDeCadastros.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "cupom")
@Table(name = "cupons")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cupom {
	
	@Id
	@Column(name = "id")
	@NotNull(message = "O id é obrigatório. ")
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "percentual_desconto")
	@NotNull(message = "O percentual de desconto é obrigatório. ")
	@Positive(message = "O percentual de desconto deve ser positivo. ")
	private Double percentualDeDesconto;
	
	@Column(name = "validade")
	@NotNull(message = "A validade é obrigatória. ")
	private Timestamp validade;
	
	@Column(name = "codigo")
	@NotNull(message = "O código é obrigatório. ")
	private String codigo;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O status do cupom é obrigatório. ")
	private Status status;
	
}
