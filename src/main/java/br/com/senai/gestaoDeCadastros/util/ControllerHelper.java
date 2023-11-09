package br.com.senai.gestaoDeCadastros.util;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ControllerHelper {
	
	public Pageable paginar(Optional<Integer> pagina) {
		if (pagina.isPresent()) {
			return PageRequest.of(pagina.get(), 15);
		} else {
			return PageRequest.of(0, 15);
		}
	}
}
