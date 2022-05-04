package br.com.zup.edu.meuscontatos.contatos.detalha;

import br.com.zup.edu.meuscontatos.contatos.Contato;

import java.util.List;
import java.util.stream.Collectors;

public class DetalheDoContatoResponse {

    private final Long id;
    private final String nome;
    private final String empresa;
    private final List<DetalheDoTelefone> telefones;

    public DetalheDoContatoResponse(Contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.empresa = contato.getEmpresa();
        this.telefones = contato.getTelefones().stream().map((t) -> {
            return new DetalheDoTelefone(t);
        }).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public List<DetalheDoTelefone> getTelefones() {
        return telefones;
    }
}
