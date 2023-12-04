package br.com.senai.gestaoDeCadastros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import br.com.senai.gestaoDeCadastros.repository.ClientesRepository;
import br.com.senai.gestaoDeCadastros.repository.UsuariosRepository;
import br.com.senai.gestaoDeCadastros.service.ClienteService;
import jakarta.validation.constraints.NotNull;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private UsuariosRepository usuariosRepository;

	@Override
	public Cliente salvar(Cliente cliente) {
		Preconditions.checkNotNull(cliente, "O cliente é obrigatório para salvar. ");
		Preconditions.checkNotNull(usuariosRepository.buscarPor(cliente.getUsuario().getId()), "O usuário informado não existe. ");
		if (cliente.getId() == null) {
			Preconditions.checkArgument(clientesRepository.buscarPorIdDoUsuario(cliente.getUsuario().getId()) == null, "O usuário já está em uso. ");
		}
		return clientesRepository.save(cliente);
	}

	@Override
	public Cliente removerPor(@NotNull(message = "O id é obrigatório para exclusão. ") Integer id) {
		Preconditions.checkNotNull(id, "O id deve ser informado para exclusão. ");
		Cliente clienteEncontrado = this.buscarPor(id);
		clientesRepository.delete(clienteEncontrado);
		return clienteEncontrado;
	}

	@Override
	public Cliente buscarPor(@NotNull(message = "O id é obrigatório para busca de cliente. ") Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório para busca. ");
		Cliente clienteEncontrado = clientesRepository.buscarPor(id);
		Preconditions.checkNotNull(clienteEncontrado, "Não foi encontrado nenhum cliente para o id informado. ");
		return clienteEncontrado;
	}

	@Override
	public Cliente buscarPorIdDoUsuario(Integer idDoUsuario) {
		Preconditions.checkNotNull(idDoUsuario, "O id do usuário é obrigatório para busca de cliente por usuário. ");
		return clientesRepository.buscarPorIdDoUsuario(idDoUsuario);
	}
	
}
