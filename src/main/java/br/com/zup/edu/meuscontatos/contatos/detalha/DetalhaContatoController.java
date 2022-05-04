package br.com.zup.edu.meuscontatos.contatos.detalha;

import br.com.zup.edu.meuscontatos.contatos.Contato;
import br.com.zup.edu.meuscontatos.contatos.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DetalhaContatoController {

    @Autowired
    private ContatoRepository repository;

    @Transactional
    @GetMapping("/api/contatos/{id}")
    public ResponseEntity<?> detalha(@PathVariable Long id) {

        Contato contato = repository.findById(id).orElseThrow(() -> {
           return new ResponseStatusException(HttpStatus.NOT_FOUND, "contato não encontrado");
        });

        return ResponseEntity
                .ok(new DetalheDoContatoResponse(contato));
    }
}
