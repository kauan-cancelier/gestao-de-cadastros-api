package br.com.senai.gestaoDeCadastros.dto;

import lombok.Data;

@Data
public class RestauranteDto {
	
	private String nome;
	
	private Integer id;
	
	private String descricao;
	
	private String status;
	
	private EnderecoDoRestauranteDto enderecoDoRestauranteDto;
	
}
