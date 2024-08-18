package com.example.cake_crud.controller;

import com.example.cake_crud.model.Cake;
import com.example.cake_crud.service.CakeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CakeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CakeService mockCakeService;

    private final Cake cake = new Cake(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"), "German Chocolate", "chocolate", "brown", 3, true, false, false);

    @Test
    public void createCake() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .post("/api/cake")
                .content(asJsonString(new Cake(null, "German Chocolate", "chocolate", "brown", 3, true, false, false)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllCakes() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/cake").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getCakeById() throws Exception {
        Mockito.when(mockCakeService.getById(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"))).thenReturn(new Cake());
        mvc.perform(MockMvcRequestBuilders
                .get("/api/cake/59c47568-fde0-4dd7-9aef-03db6a962810").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getCakeByType() throws Exception {
        Mockito.when(mockCakeService.getByType("German Chocolate")).thenReturn(List.of(new Cake()));
        mvc.perform(MockMvcRequestBuilders
                .get("/api/cake/type/German Chocolate").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void updateCake() throws Exception {
        Mockito.when(mockCakeService.getById(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"))).thenReturn(cake);
        mvc.perform( MockMvcRequestBuilders
                        .put("/api/cake/59c47568-fde0-4dd7-9aef-03db6a962810")
                        .content(asJsonString(cake))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void patchCake() throws Exception {
        Mockito.when(mockCakeService.getById(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"))).thenReturn(cake);
        mvc.perform( MockMvcRequestBuilders
                        .patch("/api/cake/59c47568-fde0-4dd7-9aef-03db6a962810")
                        .content(asJsonString(cake))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCake() throws Exception {
        Mockito.when(mockCakeService.getById(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"))).thenReturn(cake);
        mvc.perform( MockMvcRequestBuilders
                        .delete("/api/cake/59c47568-fde0-4dd7-9aef-03db6a962810")
                        .content(asJsonString(cake))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
