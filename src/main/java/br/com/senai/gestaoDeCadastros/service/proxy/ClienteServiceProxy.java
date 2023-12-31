package br.com.senai.gestaoDeCadastros.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import br.com.senai.gestaoDeCadastros.service.ClienteService;

@Service
public class ClienteServiceProxy implements ClienteService {
	
	@Autowired
	@Qualifier("clienteServiceImpl")
	private ClienteService service;

	@Override
	public Cliente salvar(Cliente cliente) {
		return service.salvar(cliente);
	}

	@Override
	public Cliente removerPor(Integer id) {
		return service.removerPor(id);
	}

	@Override
	public Cliente buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public Cliente buscarPorIdDoUsuario(Integer idDoUsuario) {
		return service.buscarPorIdDoUsuario(idDoUsuario);
	}

}
