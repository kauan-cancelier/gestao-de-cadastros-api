package br.com.senai.gestaoDeCadastros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.gestaoDeCadastros.entity.UsuarioRestaurante;
import jakarta.validation.constraints.NotNull;

@Repository
public interface UsuarioRestauranteRepository extends JpaRepository<UsuarioRestaurante, Integer>{

	@Query("SELECT ur "
			+ "FROM UsuarioRestaurante ur "
			+ "WHERE ur.idUsuario = :idDoUsuario")
	public UsuarioRestaurante buscarPelo(
			@NotNull(message = "O id do usuário é obrigatório. ")
			Integer idDoUsuario);
	
}
