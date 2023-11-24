package br.com.senai.gestaoDeCadastros.controllers;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.gestaoDeCadastros.dto.PedidoDto;

@RestController
@RequestMapping("integracoes/pedidos")
public class PedidosController {

	
	@Autowired
	private MapConverter converter;
	
	@Autowired
	private ProducerTemplate pedidoApi;
	
	@GetMapping("id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) {
		JSONObject body = new JSONObject().put("idPedido", id);
		JSONObject response = pedidoApi.requestBody("direct:buscarPedido", body, JSONObject.class);
		return ResponseEntity.ok(montarPedidoDto(response));
	}

	@GetMapping
	public ResponseEntity<?> listarPor(@RequestParam("id-restaurante") Integer id) {
		JSONObject body = new JSONObject().put("idDoRestaurante", id);
		JSONObject response = pedidoApi.requestBody("direct:listarPedidos", body, JSONObject.class);
		return ResponseEntity.ok(response);
	}
	
	private PedidoDto montarPedidoDto(JSONObject response) {
		return new PedidoDto(
				response.getInt("id_pedido"),
				response.getJSONObject("cliente").getString("nome"),
				response.getJSONObject("endereco").getString("numero"),
				response.getBigDecimal("valor total"));
	}
	
}
