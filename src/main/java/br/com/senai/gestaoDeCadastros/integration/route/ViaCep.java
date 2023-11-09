package br.com.senai.gestaoDeCadastros.integration.route;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ViaCep extends RouteBuilder {
	@Override
    public void configure() throws Exception {
    	from("direct:cep")
    	.process(new Processor() {
			@Override
            public void process(Exchange exchange) throws Exception {
            	Message message = exchange.getIn();
            	message.getBody(String.class);
            	exchange.setProperty("cep", "88703656");
			}
		})
    	.toD("https://viacep.com.br/ws/${exchangeProperty.cep}/json/")
    	.end();
    }
}
