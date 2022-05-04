package br.com.zup.edu.meuscontatos.contatos;

import javax.persistence.*;
import java.util.Objects;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_telefone_contato", columnNames = { "numero", "contato_id" })
})
@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String numero;

    @ManyToOne
    private Contato contato; // coluna: contato_id

    @Deprecated
    public Telefone(){}

    public Telefone(Long id) {
        this.id = id;
    }

    public Telefone(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
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

    public Contato getContato() {
        return contato;
    }
    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(id, telefone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
