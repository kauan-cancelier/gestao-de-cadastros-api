package br.com.senai.gestaoDeCadastros.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.gestaoDeCadastros.entity.Usuario;
import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import br.com.senai.gestaoDeCadastros.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.role = :role")
	public Page<Usuario> listarPor(
			@NotNull(message = "O papel do usuário é obrigatório para listagem. ")
			Role role,
			Pageable pageable);
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.email = :email")
	public Usuario buscarPor(
			@NotBlank(message = "O endereço de email é obrigatório para busca. ")
			String email);

	@Query(value = "SELECT u FROM Usuario u WHERE u.id = :id")
	public Usuario buscarPor(
			@NotBlank(message = "O id é obrigatório para busca. ")
			Integer id);
	
	@Modifying
	@Query("UPDATE Usuario u SET u.status = :status WHERE u.id = :id")
	public void alterarStatusPor(
			@NotNull(message = "O id é obrigatório. ")
			Integer id,
			@NotNull(message = "O status é obrigatório. ")
			Status status);

}
