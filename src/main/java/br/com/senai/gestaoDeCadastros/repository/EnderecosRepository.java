package br.com.senai.gestaoDeCadastros.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import br.com.senai.gestaoDeCadastros.entity.Endereco;

@Repository
public interface EnderecosRepository extends JpaRepository<Endereco, Integer>{

	@Query(value = "SELECT e FROM Endereco e "
			+ "JOIN FETCH e.cliente "
			+ "JOIN FETCH e.cliente.usuario "
			+ "WHERE e.id = :id ")
	public Endereco buscarPor(Integer id);
	
	@Query(value = "SELECT e FROM Endereco e "
			+ "JOIN FETCH e.cliente "
			+ "WHERE e.cliente = :cliente ")
	public Page<Endereco> listarPor(Cliente cliente, Pageable pagina);
}
