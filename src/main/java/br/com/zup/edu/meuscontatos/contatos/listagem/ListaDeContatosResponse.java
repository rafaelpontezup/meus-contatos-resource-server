package br.com.zup.edu.meuscontatos.contatos.listagem;

import br.com.zup.edu.meuscontatos.contatos.Contato;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ListaDeContatosResponse {

    private List<ContatoResponse> contatos;

    public ListaDeContatosResponse(List<Contato> contatos) {
        this.contatos = contatos.stream().map((contato) -> {
            return new ContatoResponse(contato);
        }).collect(toList());
    }

    public List<ContatoResponse> getContatos() {
        return contatos;
    }
}
