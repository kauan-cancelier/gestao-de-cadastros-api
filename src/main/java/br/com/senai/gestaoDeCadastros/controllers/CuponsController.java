package br.com.senai.gestaoDeCadastros.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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

import br.com.senai.gestaoDeCadastros.entity.Cupom;
import br.com.senai.gestaoDeCadastros.service.CupomService;
import br.com.senai.gestaoDeCadastros.util.ControllerHelper;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/cupons")
public class CuponsController {

	@Autowired
	private MapConverter converter;

	@Autowired
	@Qualifier("cupomServiceProxy")
	CupomService cupomService;

	@Autowired
	ControllerHelper helper;

	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(cupomService.buscarPor(id));
	}

	@GetMapping
	public ResponseEntity<?> listarTodos(@RequestParam("pagina") Optional<Integer> pagina) {
		Page<Cupom> page = cupomService.listarTodos(helper.paginar(pagina));
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("paginacaoAtual", page.getNumber());
		pageMap.put("totalDeItens", page.getTotalElements());
		pageMap.put("totalDePaginas", page.getTotalPages());
		
		List<Map<String, Object>> listagem = new ArrayList<Map<String, Object>>();
		for (Cupom cupom : page.getContent()) {
			listagem.add(converter(cupom));
		}
		
		pageMap.put("listagem", listagem);

		return ResponseEntity.ok(pageMap);
	}

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Cupom cupom) {
		Preconditions.checkArgument(!cupom.isPersistido(), "O cupom não pode possuir id para inserção. ");
		return ResponseEntity.ok(converter.toJsonMap(cupomService.salvar(cupom)));
	}

	@Transactional
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> excluirPor(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(converter.toJsonMap(cupomService.excluirPor(id)));
	}

	@PutMapping
	public ResponseEntity<?> alterar(@RequestBody Cupom cupom) {
		Preconditions.checkArgument(cupom.isPersistido(), "O cupom deve possuir id para alteração");
		return ResponseEntity.ok(converter.toJsonMap(cupomService.salvar(cupom)));
	}

	private Map<String, Object> converter(Cupom cupom) {
		Map<String, Object> cupomMap = new HashMap<String, Object>();
		cupomMap.put("id_cupom", cupom.getId());
		cupomMap.put("percentual_de_desconto", cupom.getPercentualDeDesconto());
		cupomMap.put("codigo", cupom.getCodigo());
		cupomMap.put("validade", cupom.getValidade());
		cupomMap.put("status", cupom.getStatus());
		return cupomMap;

	}

}
