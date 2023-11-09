package br.com.senai.gestaoDeCadastros.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class GerenciadorDeTokenJwt {
	
	@Value("${spring.jwt.secret}")
	private String secret;
	
	@Value("${spring.jwt.ttl-in-millis}")
	private int ttlInMillis;

	public String gerarTokenPor(String login) {
		return Jwts.builder()
				.setClaims(new HashMap<String, Object>())
				.setSubject(login)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + ttlInMillis))
				.signWith(getChaveDeAssinatura(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extrairEmailDo(String tokenGerado) {
		Claims detalhes = extrairDetalhesDo(tokenGerado);
		return detalhes.getSubject();
	}
	
	public Date extrairValidadeDo(String tokenGerado) {
		Claims detalhes = extrairDetalhesDo(tokenGerado);
		return detalhes.getExpiration();
	}
	
	public boolean isVencido(String tokenGerado) {
		Date validade = extrairValidadeDo(tokenGerado);
		return validade.before(new Date());
	}
	
	public boolean isValido(String tokenGerado, UserDetails credencial) {
		String login = extrairEmailDo(tokenGerado);
		boolean isLoginValido = login.equals(credencial.getUsername());
		boolean isDentroDaValidade = !isVencido(tokenGerado);
		return isLoginValido && isDentroDaValidade;
	}
	
	private Key getChaveDeAssinatura() {
		byte[] keyByte = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyByte);
	}
	
	private Claims extrairDetalhesDo(String tokenGerada) {
		return Jwts.parserBuilder()
				.setSigningKey(getChaveDeAssinatura())
				.build()
				.parseClaimsJws(tokenGerada)
				.getBody();
	}
}
