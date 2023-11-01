package br.com.senai.gestaoDeCadastros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import br.com.senai.gestaoDeCadastros.repository.ClientesRepository;
import br.com.senai.gestaoDeCadastros.service.ClienteService;
import jakarta.validation.constraints.NotNull;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	ClientesRepository repository;

	@Override
	public Cliente salvar(Cliente cliente) {
		Preconditions.checkNotNull(cliente, "O cliente é obrigatório para salvar. ");
		Preconditions.checkArgument(this.buscarPorIdDoUsuario(cliente.getUsuario().getId()) == null, "O usuário correspondente já está em uso. ");
		return repository.save(cliente);
	}

	@Override
	public Cliente removerPor(@NotNull(message = "O id é obrigatório para exclusão. ") Integer id) {
		Preconditions.checkNotNull(id, "O id deve ser informado para exclusão. ");
		Cliente clienteEncontrado = this.buscarPor(id);
		repository.delete(clienteEncontrado);
		return clienteEncontrado;
	}

	@Override
	public Cliente buscarPor(@NotNull(message = "O id é obrigatório para busca de cliente. ") Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório para busca. ");
		Cliente clienteEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(clienteEncontrado, "Não foi encontrado nenhum cliente para o id informado. ");
		return clienteEncontrado;
	}

	@Override
	public Cliente buscarPorIdDoUsuario(@NotNull(message = "O id é obrigatório para busca de cliente. ") Integer idUsuario) {
		Preconditions.checkNotNull(idUsuario, "O id é obrigatório para busca. ");
		Cliente clienteEncontrado = repository.buscarPorIdDoUsuario(idUsuario);
		Preconditions.checkNotNull(clienteEncontrado, "Não foi encontrado nenhum cliente para o id informado. ");
		return clienteEncontrado;
	}

}
