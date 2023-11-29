package br.com.senai.gestaoDeCadastros.integration.route;

import java.io.Serializable;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class FromListarPedidos extends RouteBuilder implements Serializable {

	private static final long serialVersionUID = 1L;

	private String url = "http://localhost:3000";
	
	private String authRoute = "direct:autenticarPedidos";
	
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
	        .to(authRoute)
		    .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
	        .setHeader("Authorization", simple("Bearer ${exchangeProperty.token}"))
	        .setHeader(Exchange.CONTENT_TYPE, simple("application/json;charset=UTF-8"))
	        .toD(url + "/pedidos?status=REALIZADO&pagina=0&id-restaurante=${exchangeProperty.idDoRestaurante}")
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
