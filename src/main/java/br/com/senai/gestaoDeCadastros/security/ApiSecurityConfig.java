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

import br.com.senai.gestaoDeCadastros.service.impl.CredencialDeAcessoServiceImpl;


@Configuration
public class ApiSecurityConfig {

	@Autowired
	private FiltroDeAutenticacaoJwt filtroDeAutenticacaoJwt;

	@Autowired
	private CredencialDeAcessoServiceImpl credencialDeAcessoServiceImpl;

	@Bean
	public PasswordEncoder passwordEnconder() { 
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
		authenticationProvider.setPasswordEncoder(passwordEnconder());
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
				.requestMatchers(HttpMethod.POST, "/usuarios/**")
					.hasAnyAuthority("Administrador")
				.requestMatchers(HttpMethod.PUT, "/usuarios/**")
					.hasAnyAuthority("Administrador")
				.requestMatchers(HttpMethod.PATCH, "/usuarios/**")
					.hasAnyAuthority("Administrador")
				.requestMatchers("/clientes/**")
					.hasAnyAuthority("Administrador", "Clientes")
				.anyRequest().authenticated())
		.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider()).addFilterBefore(filtroDeAutenticacaoJwt,
				UsernamePasswordAuthenticationFilter.class)
		.cors(c -> urlBasedCorsConfigurationSource());
		return http.build();
	}
	
}