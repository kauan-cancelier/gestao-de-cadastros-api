package br.com.senai.gestaoDeCadastros.util;

import java.util.HashMap;

import com.google.common.base.Preconditions;

public class UrlBuilder {
	
	private HashMap<String ,String> params = new HashMap<>();
	
	private HashMap<String, String> token = new HashMap<>();
	
	public String build (String endpoint) {
		
		StringBuilder url = new StringBuilder(endpoint);
		
		Preconditions.checkArgument(params.size() > 0,
				"O metódo build necessita de ao menos um parametro para funcionar.");
		
		for (String param : params.keySet()) {
			url.append(formatter(param) + "=" + formatter(params.get(param)));
		}
		
		if (!this.token.isEmpty()) {
			for (String token : this.token.keySet()) {
				url.append(token + this.token.get(token));
			}
		}
		System.out.println(url);
		return url.toString();
	}
	
	public UrlBuilder addParam(String nameParam, String param) {
		params.put(nameParam, param);
		return this;
	}
	
	public UrlBuilder addToken(String identifier, String token) {
		this.token.put(identifier, token);
		return this;
	}
	
	private String formatter(String param) {
		Preconditions.checkNotNull(param, "O formatter é obrigatório");
		String arr[] = param.split(" ");
		if (arr.length > 0) {
			String formattedParam = "";
			for (int i = 0; i < arr.length; i++) {
				if (i < arr.length -1) {
					formattedParam += arr[i] + "+";
				} else {
					formattedParam += arr[i];
				}
			}
			param = formattedParam;
		}
		return param;
	}
}
