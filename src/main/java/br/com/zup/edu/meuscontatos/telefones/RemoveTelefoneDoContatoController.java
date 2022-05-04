package br.com.zup.edu.meuscontatos.telefones;

import br.com.zup.edu.meuscontatos.contatos.Contato;
import br.com.zup.edu.meuscontatos.contatos.ContatoRepository;
import br.com.zup.edu.meuscontatos.contatos.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RemoveTelefoneDoContatoController {

    @Autowired
    private ContatoRepository repository;

    @Transactional
    @DeleteMapping("/api/contatos/{contatoId}/telefones/{telefoneId}")
    public ResponseEntity<?> removeTelefone(@PathVariable("contatoId") Long contatoId,
                                            @PathVariable("telefoneId") Long telefoneId) {

        Contato contato = repository.findById(contatoId).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "contato não encontrado");
        });

        contato.remove(new Telefone(telefoneId));

        return ResponseEntity
                .noContent().build();
    }

}
