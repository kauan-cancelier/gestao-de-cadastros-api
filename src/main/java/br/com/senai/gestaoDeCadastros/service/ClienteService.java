package br.com.senai.gestaoDeCadastros.service;

import org.springframework.validation.annotation.Validated;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import jakarta.validation.constraints.NotNull;

@Validated
public interface ClienteService {
	
	public Cliente salvar(
			@NotNull(message = "O cliente é obrigatório para salvar. ") 
			Cliente cliente);
	
	public Cliente removerPor(
			@NotNull(message = "O id é obrigatório para exclusão. ")
			Integer id);
	
	public Cliente buscarPor(
			@NotNull(message = "O id é obrigatório para busca de cliente. ") 
			Integer id);
	
	public Cliente buscarPorIdDoUsuario(
			@NotNull(message = "O id do usuario é obrigatório para busca de cliente pelo id do usuario. ")
			Integer idDoUsuario);
}
