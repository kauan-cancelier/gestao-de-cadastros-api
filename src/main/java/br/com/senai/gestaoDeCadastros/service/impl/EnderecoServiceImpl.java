package br.com.senai.gestaoDeCadastros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Endereco;
import br.com.senai.gestaoDeCadastros.repository.ClientesRepository;
import br.com.senai.gestaoDeCadastros.repository.EnderecosRepository;
import br.com.senai.gestaoDeCadastros.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {
	
	@Autowired
	ClientesRepository clientesRepository;
	
	@Autowired
	EnderecosRepository enderecosRepository;

	@Override
	public Endereco salvar(Endereco endereco) {
		Preconditions.checkNotNull(endereco, "O endereço é obrigatório para salvar. ");
		return enderecosRepository.save(endereco);
	}

	@Override
	public Endereco removerPor(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório para exclusão. ");
		Endereco endereco = buscarPor(id);
		enderecosRepository.delete(endereco);
		return endereco;
	}

	@Override
	public Endereco buscarPor(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório para busca. ");
		Endereco endereco = enderecosRepository.buscarPor(id);
		Preconditions.checkNotNull(endereco, "Não encontrado nenhum endereço para o id informado. ");
		return endereco;
	}

	@Override
	public Page<Endereco> listarPor(Integer idDoCliente, Pageable pagina) {
		Preconditions.checkNotNull(idDoCliente, "O cliente é obrigatório para listagem de endereços. ");
		return enderecosRepository.listarPor(idDoCliente, pagina);
	}
	
	
}
