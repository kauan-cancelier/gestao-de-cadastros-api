package br.com.senai.gestaoDeCadastros.dto;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoPeloCepDto {
	
	@NotBlank(message = "O cep é obrigatório. ")
	private String cep;
	
	@NotBlank(message = "O nome é obrigatório. ")
	private String nome;
	
	@NotBlank(message = "O numero da casa é obrigatório. ")
	private String numeroDaCasa;

	private String complemento;

	@NotNull(message = "O cliente é obrigatório. ")
	private Cliente cliente;
}
