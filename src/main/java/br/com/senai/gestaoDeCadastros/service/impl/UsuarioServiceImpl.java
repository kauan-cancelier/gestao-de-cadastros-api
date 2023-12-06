package br.com.senai.gestaoDeCadastros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Usuario;
import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import br.com.senai.gestaoDeCadastros.entity.enums.Status;
import br.com.senai.gestaoDeCadastros.repository.UsuariosRepository;
import br.com.senai.gestaoDeCadastros.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuariosRepository repository;
	
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public Usuario salvar(Usuario usuario) {
	    Preconditions.checkNotNull(usuario, "O objeto de usuário não pode ser nulo.");

	    if (usuario.getId() != null) {
	        Usuario usuarioEncontrado = repository.buscarPor(usuario.getId());
	        Preconditions.checkNotNull(usuarioEncontrado, "Nenhum usuário encontrado com esse ID.");
	        Preconditions.checkArgument(usuarioEncontrado.getStatus() == Status.A, "O usuário encontrado está inativo.");
	    }

	    Usuario usuarioExistente = repository.buscarPor(usuario.getEmail());
	    
	    if (usuarioExistente != null && !usuarioExistente.getId().equals(usuario.getId())) {
	        throw new IllegalStateException("O e-mail já está em uso por outro usuário.");
	    }

		Preconditions.checkArgument(!usuario.getSenha().isBlank() && usuario.getSenha().length() >= 3, "A senha deve ter mais do que 3 caracteres, e é obrigatória.");
	    usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

	    if (usuario.getId() == null) {
	        usuario.setStatus(Status.A);
	    }

	    return repository.save(usuario);
	}


	@Override
	public Usuario removerPor(Integer id) {
		Preconditions.checkNotNull(id, "O id deve ser informado para exclusão. ");
		Usuario usuarioEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(usuarioEncontrado, "Não foi encontrado nenhum usuário para o id informado.");
		Preconditions.checkArgument(usuarioEncontrado.getStatus() == Status.I,
				"O usuário informado está ativo. Somente um usuário inativo pode ser removido. ");
		repository.delete(usuarioEncontrado);
		return usuarioEncontrado;
	}

	@Override
	public Page<Usuario> listarPor(Role role, Pageable pageable) {
		Preconditions.checkNotNull(role, "O papel do usuário é obrigatório para listagem. ");
		return repository.listarPor(role, pageable);
	}

	@Override
	public Usuario buscarPor(String email) {
		Preconditions.checkArgument(!email.isBlank(), "O email é obrigatório para busca. ");
		Usuario usuarioEncontrado = repository.buscarPor(email);
		Preconditions.checkNotNull(usuarioEncontrado, "Não foi encontrado nenhum usuário para o id informado. ");
		Preconditions.checkArgument(usuarioEncontrado.getStatus() == Status.A, "O usuário encontrado está inativo.");
		return usuarioEncontrado;
	}

	@Override
	public Usuario buscarPor(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório para busca. ");
		Usuario usuarioEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(usuarioEncontrado, "Não foi encontrado nenhum usuário para o id informado. ");
		Preconditions.checkArgument(usuarioEncontrado.getStatus() == Status.A, "O usuário encontrado está inativo.");
		return usuarioEncontrado;
	}

	@Override
	public void alterarStatusPor(Integer id, Status status) {
		Preconditions.checkNotNull(id, "O id é obrigatório. ");
		Preconditions.checkNotNull(status, "O status é obrigatório. ");
		Usuario usuarioParaAlteracao = repository.buscarPor(id);
		Preconditions.checkNotNull(usuarioParaAlteracao, "Não foi encontrado nenhum usuário para o id informado.");
		Preconditions.checkArgument(usuarioParaAlteracao.getStatus() != status, "O status já foi salvo anteriormente. ");
		repository.alterarStatusPor(id, status);
	}
	
}