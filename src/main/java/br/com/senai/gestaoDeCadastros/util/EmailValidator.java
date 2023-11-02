package br.com.senai.gestaoDeCadastros.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

public class EmailValidator {
	
	private static final String REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
	
	/*
	 *  Começa com um ou mais caracteres alfanumérico 
	 *  Seguidos pelo "@"
	 *  Termina com um ou mais caracteres.
	 */
	
	private EmailValidator() {}
	
	public static void isValid(String email) {
		Preconditions.checkNotNull(email, "O email é obrigatório. ");
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(email);
        Preconditions.checkArgument(matcher.matches(), "O endereço de email é invalido. ");
    }

}
