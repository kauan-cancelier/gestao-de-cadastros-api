package br.com.senai.gestaoDeCadastros.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Usuario;
import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import br.com.senai.gestaoDeCadastros.repository.UsuariosRepository;
import br.com.senai.gestaoDeCadastros.service.UsuarioService;
import br.com.senai.gestaoDeCadastros.util.EmailValidator;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuariosRepository repository;

	@Override
	public Usuario salvar(Usuario usuario) {
		Preconditions.checkNotNull(usuario, "O usuário é obrigatório para salvar. ");
		EmailValidator.isValid(usuario.getEmail());
		validarEmailPersistido(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public Usuario removerPor(Integer id) {
		Preconditions.checkNotNull(id, "O id deve ser informado para exclusão. ");
		Usuario usuarioEncontrado = this.buscarPor(id);
		repository.delete(usuarioEncontrado);
		return usuarioEncontrado;
	}

	@Override
	public List<Usuario> listarPor(Role role) {
		Preconditions.checkNotNull(role, "O papel do usuário é obrigatório para listagem. ");
		return repository.listarPor(role);
	}

	@Override
	public Usuario buscarPor(String email) {
		EmailValidator.isValid(email);
		return repository.buscarPor(email);
	}

	@Override
	public Usuario buscarPor(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório para busca. ");
		Usuario usuarioEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(usuarioEncontrado, "Não foi encontrado nenhum usuário para o id informado. ");
		return usuarioEncontrado;
	}
	
	private void validarEmailPersistido(String email) {
	    if (buscarPor(email) != null) {
	        throw new IllegalArgumentException("Esse email já está salvo.");
	    }
	}
	
}