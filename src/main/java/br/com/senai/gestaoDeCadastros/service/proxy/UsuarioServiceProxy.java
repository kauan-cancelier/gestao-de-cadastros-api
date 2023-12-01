package br.com.senai.gestaoDeCadastros.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.gestaoDeCadastros.entity.Usuario;
import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import br.com.senai.gestaoDeCadastros.entity.enums.Status;
import br.com.senai.gestaoDeCadastros.service.UsuarioService;

@Service
public class UsuarioServiceProxy implements UsuarioService {
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	UsuarioService service;

	@Override
	public Usuario salvar(Usuario usuario) {
		return service.salvar(usuario);
	}

	@Override
	public Usuario removerPor(Integer id) {
		return service.removerPor(id);
	}

	@Override
	public Page<Usuario> listarPor(Role role, Pageable pageable) {
		return service.listarPor(role, pageable);
	}

	@Override
	public Usuario buscarPor(String email) {
		return service.buscarPor(email);
	}

	@Override
	public Usuario buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public void alterarStatusPor(Integer id, Status status) {
		service.alterarStatusPor(id, status);
	}

}
