package br.com.zup.edu.meuscontatos.contatos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovoContatoController {

    @Autowired
    private ContatoRepository repository;

    @Transactional
    @PostMapping("/api/contatos")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoContatoRequest request,
                                      UriComponentsBuilder uriBuilder,
                                      @AuthenticationPrincipal Jwt user) {

        String usuario = user.getClaim("preferred_username");

        Contato contato = request.toModel(usuario);
        repository.save(contato);

        URI location = uriBuilder
                .path("/api/contatos/{id}")
                .buildAndExpand(contato.getId()).toUri();

        return ResponseEntity
                .created(location).build();
    }
}
