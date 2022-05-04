package br.com.zup.edu.meuscontatos.contatos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RemoveContatoController {

    @Autowired
    private ContatoRepository repository;

    @Transactional
    @DeleteMapping("/api/contatos/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {

        Contato contato = repository.findById(id).orElseThrow(() -> {
           return new ResponseStatusException(HttpStatus.NOT_FOUND, "contato não encontrado");
        });

        repository.delete(contato);

        return ResponseEntity
                .noContent().build();
    }
}
