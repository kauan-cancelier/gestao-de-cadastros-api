package br.com.senai.gestaoDeCadastros.dto;

import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class UsuarioDto {
	
	@PositiveOrZero
	@NotNull(message = "O id é obrigatório. ")
	private Integer id;
	
	@NotBlank(message = "O email é obrigatório. ")
	private String email;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "O papel do usuário é obrigatório. ")
	private Role role;

}
