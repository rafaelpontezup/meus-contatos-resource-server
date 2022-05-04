package br.com.zup.edu.meuscontatos.contatos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TelefoneNaoEncontradoException extends RuntimeException {

    public TelefoneNaoEncontradoException(String message) {
        super(message);
    }
}
