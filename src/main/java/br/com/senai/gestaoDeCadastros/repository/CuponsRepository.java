package br.com.senai.gestaoDeCadastros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.gestaoDeCadastros.entity.Cupom;
import jakarta.validation.constraints.NotNull;

@Repository
public interface CuponsRepository extends JpaRepository<Cupom, Integer> {
	
	@Query(value = "SELECT c FROM Cupom c WHERE c.id = :id")
	public Cupom buscarPor(
			@NotNull(message = "O id é obrigatório para busca. ")
			Integer id);
	
}
