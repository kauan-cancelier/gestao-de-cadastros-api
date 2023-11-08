package br.com.senai.gestaoDeCadastros.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.gestaoDeCadastros.entity.Endereco;

@Repository
public interface EnderecosRepository extends JpaRepository<Endereco, Integer>{

	@Query("SELECT e FROM Endereco e WHERE e.id = :id")
	public Endereco buscarPor(Integer id);
	
	@Query("SELECT e FROM Endereco e WHERE e.cliente.id = :idDoCliente")
	public Page<Endereco> listarPor(Integer idDoCliente, Pageable pagina);

}
