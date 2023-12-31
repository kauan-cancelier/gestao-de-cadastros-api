package br.com.senai.gestaoDeCadastros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import br.com.senai.gestaoDeCadastros.entity.Endereco;
import br.com.senai.gestaoDeCadastros.repository.EnderecosRepository;
import br.com.senai.gestaoDeCadastros.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {
	
	@Autowired
	private EnderecosRepository enderecosRepository;
	
	@Override
	public Endereco salvar(Endereco endereco) {
		Preconditions.checkNotNull(endereco, "O endereço é obrigatório para salvar. ");
		Preconditions.checkArgument(!validarCEP(endereco.getCep()), "O cep é inválido. ");
		return enderecosRepository.save(endereco);
	}

	@Override
	public Endereco removerPor(Integer id) {
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
	public Page<Endereco> listarPor(Cliente cliente, Pageable pagina) {
		Preconditions.checkNotNull(cliente, "O cliente é obrigatório para listagem de endereços. ");
		return enderecosRepository.listarPor(cliente, pagina);
	}
	
	private boolean validarCEP(String cep) {
	    return cep.matches("\\\\d{5}-\\\\d{3}");
	}
}
