package br.com.senai.gestaoDeCadastros.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.senai.gestaoDeCadastros.entity.Cupom;
import jakarta.validation.constraints.NotNull;

@Validated
public interface CupomService {
	
	public Cupom salvar(
			@NotNull(message = "O cupom é obrigatório para salvar. ")
			Cupom cupom);
	
	public Cupom buscarPor(
			@NotNull(message = "O id é obrigatório para busca. ")
			Integer id);
	
	public Cupom excluirPor(
			@NotNull(message = "O id é obrigatório para exclusão. ")
			Integer id);

	public Page<Cupom> listarTodos(Pageable pagina);
	
}
