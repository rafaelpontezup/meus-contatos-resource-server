package br.com.zup.edu.meuscontatos.contatos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String empresa;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "contato", orphanRemoval = true
    )
    private List<Telefone> telefones = new ArrayList<>();

    @NotBlank
    @Column(nullable = false, updatable = false)
    private String criadoPor;

    @Deprecated
    public Contato(){}

    public Contato(String nome, String empresa, String criadoPor) {
        this.nome = nome;
        this.empresa = empresa;
        this.criadoPor = criadoPor;
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

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void adiciona(Telefone telefone) {
        telefone.setContato(this);
        this.telefones.add(telefone);
    }

    public void remove(Telefone telefone) {
        telefone.setContato(null);
        if (!this.telefones.remove(telefone)) {
            throw new TelefoneNaoEncontradoException("telefone não encontrado");
        }
    }
}
