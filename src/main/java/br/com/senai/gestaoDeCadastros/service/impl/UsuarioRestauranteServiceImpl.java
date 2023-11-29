package br.com.senai.gestaoDeCadastros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.UsuarioRestaurante;
import br.com.senai.gestaoDeCadastros.repository.UsuarioRestauranteRepository;
import br.com.senai.gestaoDeCadastros.service.UsuarioRestauranteService;

@Service
public class UsuarioRestauranteServiceImpl implements UsuarioRestauranteService {

	@Autowired
	private UsuarioRestauranteRepository usuarioRestauranteRepository;
	
	@Override
	public UsuarioRestaurante buscarPelo(Integer idDoUsuario) {
		Preconditions.checkNotNull(idDoUsuario, "O id do usuário é obrigatório. ");
		return usuarioRestauranteRepository.buscarPelo(idDoUsuario);
	}

}
