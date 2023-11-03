package br.com.senai.gestaoDeCadastros.excecoes;

public class IntegracaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public IntegracaoException(String mensagem) {
		super(mensagem);
	}
	
}
