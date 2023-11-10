package br.com.senai.gestaoDeCadastros.dto;

import lombok.Data;

@Data
public class EnderecoDoRestauranteDto {
	
	private String cidade;
	
	private String complemento;
	
	private String logradouro;
	
	private String bairro;
}
