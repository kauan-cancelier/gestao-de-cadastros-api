package br.com.senai.gestaoDeCadastros;

import org.apache.camel.ProducerTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;

import br.com.senai.gestaoDeCadastros.service.ClienteService;
import br.com.senai.gestaoDeCadastros.service.EnderecoService;
import br.com.senai.gestaoDeCadastros.service.UsuarioService;

@SpringBootApplication
public class Main {
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("clienteServiceImpl")
	ClienteService clienteService;
	
	@Autowired
	@Qualifier("enderecoServiceImpl")
	EnderecoService enderecoService;
	
	/*@Autowired
    private CamelContext camelContext;*/
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@Bean
	public Hibernate5JakartaModule jsonHibernate5Module() {
		return new Hibernate5JakartaModule();
	}
	 

	@Autowired
	private ProducerTemplate cardapioApi;
	
	@Autowired
	private ProducerTemplate pedidoApi;
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
//			JSONObject body = new JSONObject();
//			body.put("idDoRestaurante", 66);
//			JSONObject jsonPedidos = pedidoApi.requestBody("direct:listarPedidos", body, JSONObject.class);
//			System.out.println(jsonPedidos);
			
//			JSONObject bodyRequest = new JSONObject();
//			bodyRequest.put("idDoRestaurante", 9);
//
//			JSONObject restauranteJson = cardapioApi.requestBody("direct:cardapios", bodyRequest, JSONObject.class);
//			RestauranteDto dto = new RestauranteDto();
//			dto.setDescricao(restauranteJson.getString("descricao"));
//			dto.setId(restauranteJson.getInt("id"));
//			dto.setNome(restauranteJson.getString("nome"));
//			dto.setStatus(restauranteJson.getString("status"));
//			EnderecoDoRestauranteDto enderecoDoRestauranteDto = new EnderecoDoRestauranteDto();
//			JSONObject end = restauranteJson.getJSONObject("endereco");
//			enderecoDoRestauranteDto.setBairro(end.getString("bairro"));
//			enderecoDoRestauranteDto.setCidade(end.getString("cidade"));
//			enderecoDoRestauranteDto.setComplemento(end.getString("complemento"));
//			enderecoDoRestauranteDto.setLogradouro(end.getString("logradouro"));
//			dto.setEnderecoDoRestauranteDto(enderecoDoRestauranteDto);
//			System.out.println(dto);
			
//			Usuario usuario = new Usuario();
//			usuario.setEmail("kauanmello123@gmail.com");
//			usuario.setRole(Role.Administrador);
//			usuario.setSenha("12345678");
//			usuarioService.salvar(usuario);
			System.out.println("Running gestao de cadastros. ");
		};
	}
}
