package br.com.senai.gestaoDeCadastros.entity;

import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
	
	@Id
	@Column(name = "id")
	@NotNull(message = "O id é obrigatório. ")
	@Positive
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "email")
	@NotBlank(message = "O email é obrigatório. ")
	private String email;

	@Column(name = "senha")
	@NotBlank(message = "A senha é obrigatória. ")
	private String senha;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O papel do usuário é obrigatório. ")
	private Role role;
	
	@Column(name = "auth_token")
	@NotBlank(message = "O token de autenticação é obrigatório. ")
	private String authToken;

}
