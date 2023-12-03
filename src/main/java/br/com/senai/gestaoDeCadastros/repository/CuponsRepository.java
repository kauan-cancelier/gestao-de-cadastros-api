package br.com.senai.gestaoDeCadastros.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.gestaoDeCadastros.entity.Cupom;
import br.com.senai.gestaoDeCadastros.entity.enums.Status;
import jakarta.validation.constraints.NotNull;

@Repository
public interface CuponsRepository extends JpaRepository<Cupom, Integer> {
	
	@Query(value = "SELECT c FROM Cupom c WHERE c.id = :id")
	public Cupom buscarPor(
			@NotNull(message = "O id é obrigatório para busca. ")
			Integer id);
	
	@Query("SELECT c FROM Cupom c")
	public Page<Cupom> listarTodos(Pageable pagina);
	
	@Modifying
	@Query("UPDATE Cupom c SET c.status = :status WHERE c.id = :id")
	public void alterarStatusPor(
			@NotNull(message = "O id é obrigatório. ")
			Integer id,
			@NotNull(message = "O status é obrigatório. ")
			Status status);
}
