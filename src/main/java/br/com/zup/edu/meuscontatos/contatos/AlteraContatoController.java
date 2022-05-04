package br.com.zup.edu.meuscontatos.contatos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class AlteraContatoController {

    @Autowired
    private ContatoRepository repository;

    @Transactional
    @PutMapping("/api/contatos/{id}")
    public ResponseEntity<?> altera(@PathVariable("id") Long id, @RequestBody @Valid AlteraContatoRequest request) {

        Contato contato = repository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "contato não encontrado");
        });

        contato.setNome(request.getNome());
        contato.setEmpresa(request.getEmpresa());

        repository.save(contato);

        return ResponseEntity
                .noContent().build();
    }
}
