package br.com.zup.edu.meuscontatos.contatos.detalha;

import br.com.zup.edu.meuscontatos.contatos.Telefone;

public class DetalheDoTelefone {

    private Long id;
    private String tipo;
    private String numero;

    public DetalheDoTelefone(Telefone telefone) {
        this.id = telefone.getId();
        this.tipo = telefone.getTipo();
        this.numero = telefone.getNumero();
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }
}
