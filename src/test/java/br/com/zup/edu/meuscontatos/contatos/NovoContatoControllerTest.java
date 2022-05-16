package br.com.zup.edu.meuscontatos.contatos;

import base.SpringBootIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NovoContatoControllerTest extends SpringBootIntegrationTest {

    @Autowired
    private ContatoRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void deveCadastrarNovoAutor() throws Exception {
        // cenário
        NovoContatoRequest novoContato = new NovoContatoRequest("Alberto", "Zup", List.of(
                new TelefoneRequest("casa", "+5511988888888"),
                new TelefoneRequest("celular", "+5511999999999")
        ));

        // ação
        mockMvc.perform(POST("/api/contatos", novoContato))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("**/api/contatos/*"))
        ;

        // validação
        assertEquals(1, repository.count(), "total de contatos");
    }

    @Test
    public void deveCadastrarNovoAutor_quandoParametrosInvalidos() throws Exception {
        // cenário
        NovoContatoRequest invalido = new NovoContatoRequest("", "", null);

        // ação
        mockMvc.perform(POST("/api/contatos", invalido))
                .andExpect(status().isBadRequest())
        ;

        // validação
        assertEquals(0, repository.count(), "total de contatos");
    }
}