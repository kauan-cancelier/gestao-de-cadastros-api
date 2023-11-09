package br.com.senai.gestaoDeCadastros.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SolicitacaoDeToken {
	
	@NotBlank(message = "O email é obrigatório")
	private String email;

	@NotBlank(message = "A senha é obrigatória")
	private String senha;
}
