package br.com.senai.gestaoDeCadastros.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.senai.gestaoDeCadastros.entity.UsuarioRestaurante;
import br.com.senai.gestaoDeCadastros.service.UsuarioRestauranteService;

@Service
public class UsuarioRestauranteServiceProxy implements UsuarioRestauranteService {

	@Autowired
	@Qualifier("usuarioRestauranteServiceImpl")
	private UsuarioRestauranteService usuarioRestauranteService;
	
	@Override
	public UsuarioRestaurante buscarPelo(Integer idDoUsuario) {
		return usuarioRestauranteService.buscarPelo(idDoUsuario);
	}

}
