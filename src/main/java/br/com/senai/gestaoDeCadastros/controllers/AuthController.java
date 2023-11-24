package br.com.senai.gestaoDeCadastros.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.senai.gestaoDeCadastros.security.GerenciadorDeTokenJwt;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private GerenciadorDeTokenJwt gerenciadoDeToken;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping
	public ResponseEntity<?> logar(@RequestBody SolicitacaoDeToken solicitacao) {
		Authentication tokenAutenticado = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(solicitacao.getEmail(), solicitacao.getSenha()));
		
		Preconditions.checkArgument(tokenAutenticado.isAuthenticated(), "Email e/ou senha invalidos. ");
		
		String tokenGerado = gerenciadoDeToken.gerarTokenPor("7");
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("token", tokenGerado);
		return ResponseEntity.ok(response);
	}
}
