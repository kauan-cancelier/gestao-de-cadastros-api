package br.com.senai.gestaoDeCadastros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.senai.gestaoDeCadastros.entity.UsuarioRestaurante;

public interface UsuarioRestauranteRepository extends JpaRepository<UsuarioRestaurante, Integer>{

	@Query("SELECT ur FROM UsuarioRestaurante ur WHERE ur.idUsuario = :idDoUsuario")
	public UsuarioRestaurante buscarPelo(Integer idDoUsuario);
	
}
