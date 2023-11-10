package br.com.senai.gestaoDeCadastros.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.senai.gestaoDeCadastros.entity.Cupom;
import br.com.senai.gestaoDeCadastros.service.CupomService;

@Service
public class CupomServiceProxy implements CupomService {
	
	@Autowired
	@Qualifier("cupomServiceImpl")
	private CupomService service;
	
	@Override
	public Cupom salvar(Cupom cupom) {
		return service.salvar(cupom);
	}

	@Override
	public Cupom buscarPor(Integer id) {
		return service.buscarPor(id);
	}

	@Override
	public Cupom excluirPor(Integer id) {
		return service.excluirPor(id);
	}

}
