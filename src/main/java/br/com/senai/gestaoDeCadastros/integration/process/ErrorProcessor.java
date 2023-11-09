package br.com.senai.gestaoDeCadastros.integration.process;

import java.io.Serializable;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import br.com.senai.gestaoDeCadastros.excecoes.IntegracaoException;

@Component
public class ErrorProcessor implements Processor, Serializable {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void process(Exchange exchange) throws Exception {
		Exception error = exchange.getProperty("error", Exception.class);
		error.printStackTrace();
		throw new IntegracaoException(error.getMessage());
	}

}