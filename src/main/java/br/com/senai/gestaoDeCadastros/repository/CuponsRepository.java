package br.com.senai.gestaoDeCadastros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.gestaoDeCadastros.entity.Cupom;

@Repository
public interface CuponsRepository extends JpaRepository<Cupom, Integer> {

}
