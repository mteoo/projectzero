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
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SourceController.class)
public class SourceControllerTest {

    @MockBean
    private BasicAuthEntryPoint basicAuthEntryPoint;

    @MockBean PessoasRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarUrl() throws Exception {
        mockMvc.perform(get("/source")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("https://github.com/mteoo/projectzero"));
    }
}
