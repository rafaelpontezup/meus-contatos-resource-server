package br.com.zup.edu.meuscontatos.contatos.detalha;

import base.SpringBootIntegrationTest;
import br.com.zup.edu.meuscontatos.contatos.Contato;
import br.com.zup.edu.meuscontatos.contatos.ContatoRepository;
import br.com.zup.edu.meuscontatos.contatos.Telefone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DetalhaContatoControllerTest extends SpringBootIntegrationTest {

    @Autowired
    private ContatoRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void deveDetalharAutorExistente() throws Exception {
        // cenário
        Contato contato = new Contato("Rafael","Zup", "jordi.silva");
        contato.adiciona(
                new Telefone("celular", "+5511988888888")
        );
        repository.save(contato);

        // ação e validação
        mockMvc.perform(GET("/api/contatos/{id}", contato.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(contato.getId()))
                .andExpect(jsonPath("$.nome").value(contato.getNome()))
                .andExpect(jsonPath("$.empresa").value(contato.getEmpresa()))
                .andExpect(jsonPath("$.criadoPor").value(contato.getCriadoPor()))
                .andExpect(jsonPath("$.telefones").exists())
                .andExpect(jsonPath("$.telefones").isNotEmpty())
        ;
    }

    @Test
    public void naoDeveDetalharAutor_quandoNaoEncontrado() throws Exception {
        // cenário
        Contato contato = new Contato("Rafael","Zup", "jordi.silva");
        contato.adiciona(
                new Telefone("celular", "+5511988888888")
        );
        repository.save(contato);

        // ação e validação
        mockMvc.perform(GET("/api/contatos/{id}", -99999))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("contato não encontrado"))
        ;
    }

}