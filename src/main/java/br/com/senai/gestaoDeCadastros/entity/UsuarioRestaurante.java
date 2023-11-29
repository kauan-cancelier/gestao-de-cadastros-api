package br.com.senai.gestaoDeCadastros.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "UsuarioRestaurante")
@Table(name = "usuarios_restaurantes")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioRestaurante {
	
	@Id
	@Column(name = "id_usuario")
	@Positive
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	@Column(name = "id_restaurante")
	@Positive
	private Integer idRestaurante;
	
}
