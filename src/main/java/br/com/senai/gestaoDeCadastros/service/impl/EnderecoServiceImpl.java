package br.com.senai.gestaoDeCadastros.service.impl;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
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
	EnderecosRepository enderecosRepository;
	
	@Autowired
    private CamelContext camelContext;

	@Override
	public Endereco salvar(Endereco endereco) {
		Preconditions.checkNotNull(endereco, "O endereço é obrigatório para salvar. ");
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
	
	public Endereco buscarEnderecoPelo(String cep) {
		Preconditions.checkNotNull(cep, "O cep é obrigatório");
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        JSONObject cepEncontrado = new JSONObject(producerTemplate.requestBody("direct:cep", null, String.class));
        Preconditions.checkNotNull(cepEncontrado, "Nenhum endereço encontrado para o cep informado. ");
		return montarEndereco(cepEncontrado);
	}
	
	private Endereco montarEndereco(JSONObject json) {
		Endereco endereco = new Endereco();
        endereco.setBairro(json.getString("bairro"));
        endereco.setCep(json.getString("cep"));
        endereco.setCidade(json.getString("localidade"));
        endereco.setEstado(json.getString("uf"));
        return endereco;
	}
	
}
