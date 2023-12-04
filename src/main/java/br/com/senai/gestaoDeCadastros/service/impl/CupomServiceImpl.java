package br.com.senai.gestaoDeCadastros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Cupom;
import br.com.senai.gestaoDeCadastros.entity.enums.Status;
import br.com.senai.gestaoDeCadastros.repository.CuponsRepository;
import br.com.senai.gestaoDeCadastros.service.CupomService;

@Service
public class CupomServiceImpl implements CupomService {
	
	@Autowired
	private CuponsRepository cuponsRepository;

	@Override
	public Cupom salvar(Cupom cupom) {
		Preconditions.checkNotNull(cupom, "O cupom é obrigatório para salvar. ");
		if (cupom.getId() != null) {
			Cupom cupomEncontrado = cuponsRepository.buscarPor(cupom.getId());
	        Preconditions.checkNotNull(cupomEncontrado, "Nenhum cupom encontrado com esse ID.");
	        Preconditions.checkArgument(cupomEncontrado.getStatus() == Status.A, "O cupom encontrado está inativo.");
	    }
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
		Preconditions.checkNotNull(pagina, "A pagina é obrigatória. ");
		return cuponsRepository.listarTodos(pagina);
	}

	@Override
	public void alterarStatusPor(Integer id, Status status) {
		Preconditions.checkNotNull(id, "O id é obrigatório. ");
		Preconditions.checkNotNull(status, "O status é obrigatório. ");
		Cupom cupomParaAlteracao = buscarPor(id);
		Preconditions.checkArgument(cupomParaAlteracao.getStatus() != status, "O status já foi salvo anteriormente. ");
		cuponsRepository.alterarStatusPor(id, status);
	}

}
