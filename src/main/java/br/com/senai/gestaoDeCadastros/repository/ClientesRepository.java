package br.com.senai.gestaoDeCadastros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer>{
	
	@Query(value = "SELECT c FROM Cliente c "
			+ "JOIN FETCH c.usuario "
			+ "WHERE c.id = :id ")
	public Cliente buscarPor(
			@NotBlank(message = "O id é obrigatório para busca. ")
			Integer id);
	
	@Query(value = "SELECT c FROM Cliente c "
			+ "JOIN FETCH c.usuario "
			+ "WHERE c.usuario.id = :idUsuario ")
	public Cliente buscarPorIdDoUsuario(
			@NotNull(message = "O usuário é obrigatório para busca. ")
			Integer idUsuario);
}
