package br.com.senai.gestaoDeCadastros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import br.com.senai.gestaoDeCadastros.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private MapConverter converter;
	
	@Autowired
	@Qualifier("clienteServiceProxy")
	ClienteService clienteService;
	
	@GetMapping("id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(converter.toJsonMap(clienteService.buscarPor(id)));
	}
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Cliente cliente) {
		Preconditions.checkArgument(!cliente.isPersistido(), "O cliente não pode possuir id para inserção");
		return ResponseEntity.ok(converter.toJsonMap(clienteService.salvar(cliente)));
	}
	
}
