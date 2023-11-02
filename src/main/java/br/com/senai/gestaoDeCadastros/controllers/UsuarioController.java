package br.com.senai.gestaoDeCadastros.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.senai.gestaoDeCadastros.entity.Usuario;
import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import br.com.senai.gestaoDeCadastros.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private MapConverter converter;
	
	@Autowired
	@Qualifier("usuarioServiceProxy")
	UsuarioService usuarioService;
	
	@GetMapping("id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(converter.toJsonMap(usuarioService.buscarPor(id)));
	}
	
	
	@GetMapping
	public ResponseEntity<?> listarPor(@RequestParam("role") Role role, @RequestParam("pagina")  Optional<Integer> pagina) {
		Pageable paginacao;
		if (pagina.isPresent()) {
			paginacao = PageRequest.of(pagina.get(), 15);
		} else {
			paginacao = PageRequest.of(0, 15);
		}
		return ResponseEntity.ok(converter.toJsonMap(usuarioService.listarPor(role, paginacao)));
	}
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Usuario usuario) {
		Preconditions.checkArgument(!usuario.isPersistido(), "O usuario não pode possuir id para inserção. ");
		return ResponseEntity.ok(converter.toJsonMap(usuarioService.salvar(usuario)));
	}
	
}
