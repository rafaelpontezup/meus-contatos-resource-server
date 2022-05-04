package br.com.zup.edu.meuscontatos.contatos.listagem;

import br.com.zup.edu.meuscontatos.contatos.Contato;
import br.com.zup.edu.meuscontatos.contatos.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListaContatosController {

    @Autowired
    private ContatoRepository repository;

    @GetMapping("/api/contatos")
    public ResponseEntity<?> lista() {

        List<Contato> contatos = repository.findAll();

        return ResponseEntity
                .ok(new ListaDeContatosResponse(contatos));
    }

}
