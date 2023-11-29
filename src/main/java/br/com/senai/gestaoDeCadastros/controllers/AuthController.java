package br.com.senai.gestaoDeCadastros.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.dto.SolicitacaoDeToken;
import br.com.senai.gestaoDeCadastros.entity.Cliente;
import br.com.senai.gestaoDeCadastros.entity.Usuario;
import br.com.senai.gestaoDeCadastros.entity.UsuarioRestaurante;
import br.com.senai.gestaoDeCadastros.security.GerenciadorDeTokenJwt;
import br.com.senai.gestaoDeCadastros.service.ClienteService;
import br.com.senai.gestaoDeCadastros.service.UsuarioRestauranteService;
import br.com.senai.gestaoDeCadastros.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private GerenciadorDeTokenJwt gerenciadoDeToken;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	@Qualifier("clienteServiceProxy")
	private ClienteService clienteService;
	
	@Autowired
	@Qualifier("usuarioServiceProxy")
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("usuarioRestauranteServiceProxy")
	private UsuarioRestauranteService usuarioRestauranteService;

	@PostMapping
	public ResponseEntity<?> logar(@RequestBody SolicitacaoDeToken solicitacao) {
		Authentication tokenAutenticado = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(solicitacao.getEmail(), solicitacao.getSenha()));
		
		Preconditions.checkArgument(tokenAutenticado.isAuthenticated(), "Email e/ou senha invalidos. ");
		
		Usuario usuario = usuarioService.buscarPor(solicitacao.getEmail());
		
		Cliente cliente = clienteService.buscarPorIdDoUsuario(usuario.getId());
		String idCliente = "";
		if (cliente == null) {
			idCliente = "Sem cliente";
		} else {
			idCliente = cliente.getId().toString();
		}
		
		UsuarioRestaurante usuarioRestaurante = usuarioRestauranteService.buscarPelo(usuario.getId());
		String idRestaurante = "";
		if (usuarioRestaurante == null) {
			idRestaurante = "Cliente comum";
		} else {
			idRestaurante = usuarioRestaurante.getIdRestaurante().toString();
		}
		
		String tokenGerado = gerenciadoDeToken.gerarTokenPor(
				solicitacao.getEmail(),
				idCliente,
				idRestaurante,
				usuario.getRole()
			);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("token", tokenGerado);
		return ResponseEntity.ok(response);
	}
}
