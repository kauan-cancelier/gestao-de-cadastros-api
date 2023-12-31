package br.com.senai.gestaoDeCadastros.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.gestaoDeCadastros.entity.Cupom;
import br.com.senai.gestaoDeCadastros.entity.enums.Status;
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

	@Override
	public Page<Cupom> listarTodos(Pageable pagina) {
		return service.listarTodos(pagina);
	}

	@Override
	public void alterarStatusPor(Integer id, Status status) {
		service.alterarStatusPor(id, status);
	}

}
