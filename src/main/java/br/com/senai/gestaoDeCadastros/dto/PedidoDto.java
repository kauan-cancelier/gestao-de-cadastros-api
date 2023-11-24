package br.com.senai.gestaoDeCadastros.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {

	private Integer numeroDoPedido;
	
	private String nomeDoCliente;
	
	private String endereco;
	
	private BigDecimal valorTotal;
	
	
	
}
