package br.com.senai.gestaoDeCadastros.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Endereco;
import br.com.senai.gestaoDeCadastros.service.EnderecoService;
import br.com.senai.gestaoDeCadastros.util.ControllerHelper;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private MapConverter converter;
	
	@Autowired
	@Qualifier("enderecoServiceProxy")
	EnderecoService enderecoService;
	
	@GetMapping("id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(enderecoService.buscarPor(id));
	}
	
	@GetMapping
	public ResponseEntity<?> listarPor(@RequestParam("idDoCliente") Integer idDoCliente, @RequestParam("pagina")  Optional<Integer> pagina) {
		return ResponseEntity.ok(converter.toJsonMap(enderecoService.listarPor(idDoCliente, ControllerHelper.paginar(pagina))));
	}
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Endereco endereco) {
		Preconditions.checkArgument(!endereco.isPersistido(), "O endereco não pode possuir id para inserção. ");
		return ResponseEntity.ok(converter.toJsonMap(enderecoService.salvar(endereco)));
	}
	
	@Transactional
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> excluirPor(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(converter.toJsonMap(enderecoService.removerPor(id)));
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(@RequestBody Endereco endereco) {
		Preconditions.checkArgument(endereco.isPersistido(), "O endereco deve possuir id para alteração");
		return ResponseEntity.ok(converter.toJsonMap(enderecoService.salvar(endereco)));
	}
	
}
