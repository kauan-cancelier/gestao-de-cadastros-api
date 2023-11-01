package br.com.senai.gestaoDeCadastros;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;

import br.com.senai.gestaoDeCadastros.entity.Cliente;
import br.com.senai.gestaoDeCadastros.entity.enums.Sexo;
import br.com.senai.gestaoDeCadastros.service.ClienteService;
import br.com.senai.gestaoDeCadastros.service.UsuarioService;

@SpringBootApplication
public class Main {
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("clienteServiceImpl")
	ClienteService clienteService;
	
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
			Cliente cliente = new Cliente();
			cliente.setNome("Kauan Mello");
			cliente.setSexo(Sexo.M);
			cliente.setDataDeNascimento(Date.valueOf("2005-08-26"));
			cliente.setUsuario(usuarioService.buscarPor(2));
			System.out.println(clienteService.salvar(cliente));
		};
	}
}
