package br.com.senai.gestaoDeCadastros;

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
//			Usuario usuario = new Usuario();
//			usuario.setEmail("kauanmello123@gmail.com");
//			usuario.setRole(Role.Administrador);
//			usuario.setSenha("12345678");
//			usuarioService.salvar(usuario);
			System.out.println("Running gestao de cadastros. ");
		};
	}
}
