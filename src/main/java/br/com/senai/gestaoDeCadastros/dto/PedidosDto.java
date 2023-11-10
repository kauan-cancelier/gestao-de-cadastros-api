package br.com.senai.gestaoDeCadastros.dto;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import lombok.Data;

@Data
public class PedidosDto {

	private Integer id;
	
	private String status;
	
	private String retirada;
	
	private Double valorTotal;
	
	private Double valorItens;
	
	private Double valorDesconto;
	
	private Double valorFrete;
	
	private Cliente cliente;
	
}
