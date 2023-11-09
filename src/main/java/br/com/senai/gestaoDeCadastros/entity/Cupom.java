package br.com.senai.gestaoDeCadastros.entity;

import java.sql.Date;

import br.com.senai.gestaoDeCadastros.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Cupom")
@Table(name = "cupons")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cupom {
	
	@Id
	@Column(name = "id")
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "porcentagem")
	@NotNull(message = "O percentual de desconto é obrigatório. ")
	@Positive(message = "O percentual de desconto deve ser positivo. ")
	private Double percentualDeDesconto;
	
	@Column(name = "validade")
	@NotNull(message = "A validade é obrigatória. ")
	private Date validade;
	
	@Column(name = "codigo")
	@NotNull(message = "O código é obrigatório. ")
	private String codigo;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O status do cupom é obrigatório. ")
	private Status status;
	
}
