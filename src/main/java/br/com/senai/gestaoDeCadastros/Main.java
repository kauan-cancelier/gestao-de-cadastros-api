package br.com.senai.gestaoDeCadastros;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
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
	
	@Autowired
    private CamelContext camelContext;
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@Bean
	public Hibernate5JakartaModule jsonHibernate5Module() {
		return new Hibernate5JakartaModule();
	}
	 
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
            ProducerTemplate producerTemplate = camelContext.createProducerTemplate();

            String response = producerTemplate.requestBody("direct:cep", null, String.class);

            System.out.println("Response from ViaCep: " + response);
		};
	}
}
