package br.com.senai.gestaoDeCadastros.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import br.com.senai.gestaoDeCadastros.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
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
	@Positive
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "email")
	@NotBlank(message = "O email é obrigatório. ")
	@Email(message = "O email deve seguir o padrão example@example.com")
	private String email;

	@Column(name = "senha")
	@NotBlank(message = "A senha é obrigatória. ")
	private String senha;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O papel do usuário é obrigatório. ")
	private Role role;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O status do usuário é obrigatório. ")
	private Status status;
	
	@JsonIgnore
	@Transient
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}
	
}
