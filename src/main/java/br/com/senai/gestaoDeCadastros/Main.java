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

@SpringBootApplication
public class Main {

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
			System.out.println("Running gestao de cadastros. ");
		};
	}
}
