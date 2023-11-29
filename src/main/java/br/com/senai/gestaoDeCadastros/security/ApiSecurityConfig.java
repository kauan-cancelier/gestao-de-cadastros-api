package br.com.senai.gestaoDeCadastros.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.senai.gestaoDeCadastros.entity.enums.Role;
import br.com.senai.gestaoDeCadastros.service.impl.CredencialDeAcessoServiceImpl;


@Configuration
public class ApiSecurityConfig {
	
	private final String ADMINISTRADOR = Role.Administrador.toString();
	
	private final String CLIENTE = Role.Cliente.toString();
	
	private final String RESTAURANTE = Role.Restaurante.toString();
	
	@Autowired
	private FiltroDeAutenticacaoJwt filtroDeAutenticacaoJwt;

	@Autowired
	private CredencialDeAcessoServiceImpl credencialDeAcessoServiceImpl;

	@Bean
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
			throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(credencialDeAcessoServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	private UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
		CorsConfiguration cors = new CorsConfiguration();
		cors.applyPermitDefaultValues();
		cors.setAllowedHeaders(Arrays.asList("*"));
		cors.setAllowedMethods(Arrays.asList("*"));
		cors.setAllowedOrigins(Arrays.asList("*"));
		cors.setExposedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource ccs = new UrlBasedCorsConfigurationSource();
		ccs.registerCorsConfiguration("/**", cors);
		return ccs;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(
				request -> request.requestMatchers("/auth/**")
				.permitAll()
				.requestMatchers(HttpMethod.POST, "/usuarios")
					.permitAll()
				.requestMatchers("/usuarios/**")
					.hasAnyAuthority(ADMINISTRADOR, RESTAURANTE)
				.requestMatchers("/clientes/**")
					.hasAnyAuthority(ADMINISTRADOR, RESTAURANTE)
				.requestMatchers("/enderecos/**")
					.hasAnyAuthority(ADMINISTRADOR, RESTAURANTE)
				.requestMatchers("/cupons/**")
					.hasAnyAuthority(ADMINISTRADOR, CLIENTE, RESTAURANTE)
				.requestMatchers("/integracoes/**")
					.hasAnyAuthority(ADMINISTRADOR, RESTAURANTE)
				.anyRequest().authenticated())
		.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider()).addFilterBefore(filtroDeAutenticacaoJwt,
				UsernamePasswordAuthenticationFilter.class)
		.cors(c -> urlBasedCorsConfigurationSource());
		return http.build();
	}
	
}