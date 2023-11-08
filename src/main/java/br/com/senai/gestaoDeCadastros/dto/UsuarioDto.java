package br.com.senai.gestaoDeCadastros.dto;

import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UsuarioDto {
	
	@Positive
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O email é obrigatório. ")
	private String email;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "O papel do usuário é obrigatório. ")
	private Role role;

}
