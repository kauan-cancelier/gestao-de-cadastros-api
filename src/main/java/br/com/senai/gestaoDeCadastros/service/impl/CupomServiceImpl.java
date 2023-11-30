package br.com.senai.gestaoDeCadastros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Cupom;
import br.com.senai.gestaoDeCadastros.repository.CuponsRepository;
import br.com.senai.gestaoDeCadastros.service.CupomService;

@Service
public class CupomServiceImpl implements CupomService {
	
	@Autowired
	CuponsRepository cuponsRepository;

	@Override
	public Cupom salvar(Cupom cupom) {
		Preconditions.checkNotNull(cupom, "O cupom é obrigatório para salvar. ");
		return cuponsRepository.save(cupom);
	}

	@Override
	public Cupom buscarPor(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório para busca. ");
		Cupom cupom = cuponsRepository.buscarPor(id);
		Preconditions.checkNotNull(cupom, "Não foi encontrado nenhum cupom para o id informado. ");
		return cupom;
	}

	@Override
	public Cupom excluirPor(Integer id) {
		Cupom cupom = buscarPor(id);
		cuponsRepository.delete(cupom);
		return cupom;
	}

	@Override
	public Page<Cupom> listarTodos(Pageable pagina) {
		return cuponsRepository.listarTodos(pagina);
	}

}
