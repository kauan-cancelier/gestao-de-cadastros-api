package br.com.senai.gestaoDeCadastros.service;

import org.springframework.validation.annotation.Validated;

import br.com.senai.gestaoDeCadastros.entity.UsuarioRestaurante;
import jakarta.validation.constraints.NotNull;

@Validated
public interface UsuarioRestauranteService {
	
	public UsuarioRestaurante buscarPelo(
			@NotNull(message = "O id do usuário é obrigatório para busca. ")
			Integer idDoUsuario);

}
