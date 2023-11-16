package br.com.senai.gestaoDeCadastros.integration.route;

import java.io.Serializable;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class FromListarPedidos extends RouteBuilder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void configure() throws Exception {
	    from("direct:listarPedidos")
	        .process(new Processor() {
	            @Override
	            public void process(Exchange exchange) throws Exception {
	            	JSONObject body = new JSONObject(exchange.getMessage().getBody(String.class));
	            	exchange.setProperty("idDoRestaurante", body.get("idDoRestaurante"));
	            }
	        })
	        .toD("http://localhost:3000/pedidos?status=REALIZADO&pagina=0&id-restaurante=${exchangeProperty.idDoRestaurante}")
	        	.process(new Processor() {
					
					@Override
					public void process(Exchange exchange) throws Exception {
						String responseJson = exchange.getIn().getBody(String.class);
						JSONObject jsonObject = new JSONObject(responseJson);
						exchange.getMessage().setBody(jsonObject.toString());
					}
				})
	        .end();
	}


}
