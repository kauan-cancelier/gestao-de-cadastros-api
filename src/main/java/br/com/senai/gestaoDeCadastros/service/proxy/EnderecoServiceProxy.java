package br.com.senai.gestaoDeCadastros.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import br.com.senai.gestaoDeCadastros.entity.Endereco;
import br.com.senai.gestaoDeCadastros.service.EnderecoService;

public class EnderecoServiceProxy implements EnderecoService {

	@Autowired
	@Qualifier("enderecoServiceImpl")
	private EnderecoService service;
	
	
	@Override
	public Endereco salvar(Endereco endereco) {
		return service.salvar(endereco);
	}

	@Override
	public Endereco removerPor(Integer id) {
		return service.removerPor(id);
	}

	@Override
	public Endereco buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public Page<Endereco> listarPor(Integer idDoCliente) {
		return service.listarPor(idDoCliente);
	}

}
