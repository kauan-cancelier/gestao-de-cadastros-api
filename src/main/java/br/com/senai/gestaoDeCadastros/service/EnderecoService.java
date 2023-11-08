package br.com.senai.gestaoDeCadastros.service;

import org.springframework.validation.annotation.Validated;

import br.com.senai.gestaoDeCadastros.entity.Endereco;
import jakarta.validation.constraints.NotNull;

@Validated
public interface EnderecoService {

	public Endereco salvar(
			@NotNull(message = "O endereco é obrigatório para salvar. ") 
			Endereco endereco);
	
	public Endereco removerPor(
			@NotNull(message = "O id é obrigatório para exclusão. ")
			Integer id);
	
	public Endereco buscarPor(
			@NotNull(message = "O id é obrigatório para busca de endereco. ") 
			Integer id);
	
}
