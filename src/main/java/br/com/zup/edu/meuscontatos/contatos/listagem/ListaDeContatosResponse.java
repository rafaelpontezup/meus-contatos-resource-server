package br.com.zup.edu.meuscontatos.contatos.listagem;

import br.com.zup.edu.meuscontatos.contatos.Contato;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ListaDeContatosResponse {

    @Deprecated
    private ListaDeContatosResponse(){}

    public static List<ContatoResponse> of(List<Contato> contatos) {
        return contatos.stream().map((contato) -> {
            return new ContatoResponse(contato);
        }).collect(toList());
    }

}
