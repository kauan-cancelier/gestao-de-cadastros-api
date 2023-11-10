package br.com.senai.gestaoDeCadastros.integration.route;

import java.io.Serializable;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CardapiosApi extends RouteBuilder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String url = "https://cardapios-mktplace-api-production.up.railway.app";
	
	private String authUrl = url + "/auth";
	
	@Value("${cardapios.login}")
	private String login;
	
	@Value("${cardapios.senha}")
	private String senha;
	

	@Override
    public void configure() throws Exception {
        from("direct:cardapios")
        .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
        .setHeader(Exchange.CONTENT_TYPE, simple("application/json;charset=UTF-8"))
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
            	
            	JSONObject body = new JSONObject(exchange.getMessage().getBody(String.class));
            	exchange.setProperty("idDoRestaurante", body.get("idDoRestaurante"));
            	
                JSONObject requestBodyJson = new JSONObject();
                requestBodyJson.put("login", login);
                requestBodyJson.put("senha", senha);
                exchange.getMessage().setBody(requestBodyJson.toString());;
            }
        })
        .to(authUrl)
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                Message message = exchange.getIn();
                String responseJson = message.getBody(String.class);
                JSONObject jsonObject = new JSONObject(responseJson);
                String token = jsonObject.getString("token");
                exchange.setProperty("token", token);
            }
        })
        .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
        .setHeader("Authorization", simple("Bearer ${exchangeProperty.token}"))
        .setHeader(Exchange.CONTENT_TYPE, simple("application/json;charset=UTF-8"))
        .toD(url + "/restaurantes/id/${exchangeProperty.idDoRestaurante}")
        .process(new Processor() {		
			@Override
			public void process(Exchange exchange) throws Exception {
				JSONObject restauranteJson = new JSONObject(exchange.getMessage().getBody(String.class));
				exchange.getMessage().setBody(restauranteJson.toString());
			}
		})
        .end();
    }

}
