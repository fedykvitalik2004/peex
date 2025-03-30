package org.vitalii.fedyk.peex.testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.vitalii.fedyk.peex.testing.Toy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ToyController.class)
@AutoConfigureMockMvc(addFilters = false)
class ToyControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private Toy toy;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        given(toy.get(any(String.class))).willReturn(toy);
        ResultActions response = mockMvc.perform(post("/api/toys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(toy)));

        response.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Toy")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetToy() throws Exception {
        final String name = "Hello";
        when(toy.getName()).thenReturn(name);
        ResultActions result = mockMvc.perform(get("/api/toys")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "1")
        );

        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is(name + " 1")));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(toy).say(anyString());

        ResultActions result = mockMvc.perform(delete("/api/toys/1")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }
}
