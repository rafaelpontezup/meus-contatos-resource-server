package br.com.zup.edu.meuscontatos.contatos.listagem;

import br.com.zup.edu.meuscontatos.contatos.Contato;

public class ContatoResponse {

    private final Long id;
    private final String nome;
    private final String empresa;

    public ContatoResponse(Contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.empresa = contato.getEmpresa();
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

}
