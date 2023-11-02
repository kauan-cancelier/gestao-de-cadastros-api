package br.com.senai.gestaoDeCadastros.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.senai.gestaoDeCadastros.entity.Usuario;
import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import jakarta.validation.constraints.NotNull;

@Validated
public interface UsuarioService {
	
	public Usuario salvar(
			@NotNull(message = "O usuario é obrigatório para salvar. ") 
			Usuario usuario);
	
	public Usuario removerPor(
			@NotNull(message = "O id é obrigatório para exclusão. ")
			Integer id);
	
	public Page<Usuario> listarPor(
			@NotNull(message = "O papel é obrigatório para listagem. ")
			Role role, 
			Pageable pageable);

	public Usuario buscarPor(
			@NotNull(message = "O email é obrigatório para busca de usuário. ")
			String email);
	
	public Usuario buscarPor(
			@NotNull(message = "O id é obrigatório para busca de usuário") 
			Integer id);
}
