package com.matheus.dias.projectzero.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matheus.dias.projectzero.core.auth.BasicAuthEntryPoint;
import com.matheus.dias.projectzero.models.Pessoas;
import com.matheus.dias.projectzero.repositories.PessoasRepository;
import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PessoasController.class)
public class PessoasControllerTest {

    @MockBean
    private PessoasRepository repository;

    @MockBean
    private BasicAuthEntryPoint basicAuthEntryPoint;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "test", password = "pass")
    public void deveBuscarTodos() throws Exception {
        Pessoas pessoa = new Pessoas();
        pessoa.setNome("teste");
        pessoa.setCpf("42652337831");
        when(repository.findAll()).thenReturn(Collections.singletonList(pessoa));
        mockMvc.perform(get("/pessoas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0]nome").value(pessoa.getNome()))
                .andExpect(jsonPath("[0]cpf").value(pessoa.getCpf()));
    }

    @Test
    @WithMockUser(username = "test", password = "pass")
    public void deveBuscarUm() throws Exception {
        Pessoas pessoa = new Pessoas();
        pessoa.setNome("teste");
        pessoa.setCpf("42652337831");

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(pessoa));
        mockMvc.perform(get("/pessoas/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("nome").value(pessoa.getNome()))
                .andExpect(jsonPath("cpf").value(pessoa.getCpf()));
    }

    @Test
    @WithMockUser(username = "test", password = "pass")
    public void deveCriarPessoa() throws Exception {
        Pessoas pessoa = new Pessoas();
        pessoa.setNome("teste");
        pessoa.setCpf("42652337831");

        when(repository.exists(any(Predicate.class))).thenReturn(false);
        when(repository.save(any(Pessoas.class))).thenReturn(pessoa);
        mockMvc.perform(post("/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("nome").value(pessoa.getNome()))
                .andExpect(jsonPath("cpf").value(pessoa.getCpf()));
    }

    @Test
    @WithMockUser(username = "test", password = "pass")
    public void deveAtualizarPessoa() throws Exception {
        Pessoas pessoa = new Pessoas();
        pessoa.setId(1l);
        pessoa.setNome("teste");
        pessoa.setCpf("42652337831");

        Pessoas pessoaAtualizada = new Pessoas();
        pessoa.setId(1l);
        pessoa.setNome("atualizou");
        pessoa.setCpf("11722573880");

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(pessoa));
        when(repository.save(any(Pessoas.class))).thenReturn(pessoaAtualizada);
        mockMvc.perform(put("/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("nome").value(pessoaAtualizada.getNome()))
                .andExpect(jsonPath("cpf").value(pessoaAtualizada.getCpf()));
    }

    @Test
    @WithMockUser(username = "test", password = "pass")
    public void deveRetornarNotFoundAoAtualizar() throws Exception {
        Pessoas pessoa = new Pessoas();
        pessoa.setId(1l);
        pessoa.setNome("teste");
        pessoa.setCpf("42652337831");

        mockMvc.perform(put("/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "test", password = "pass")
    public void deveDeletarPessoa() throws Exception {
        when(repository.exists(any(Predicate.class))).thenReturn(true);
        mockMvc.perform(delete("/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "test", password = "pass")
    public void deveRetornarNotFound() throws Exception {
        when(repository.exists(any(Predicate.class))).thenReturn(false);
        mockMvc.perform(delete("/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
