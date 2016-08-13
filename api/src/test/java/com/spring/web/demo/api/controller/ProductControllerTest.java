package com.spring.web.demo.api.controller;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = webAppContextSetup(context)
                .build();
    }

    @Test
    public void list() throws Exception {
        mvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    public void create() throws Exception {
        mvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"sku\":\"sku\"}".getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("iPhone 5s")));
    }

    @Test
    public void update() throws Exception {
        mvc.perform(put("/api/product/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"sku\":\"sku\"}".getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("iPhone 5s")));
    }

    @Test
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/product/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void search() throws Exception {
        mvc.perform(get("/api/product/search")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"sku\":\"sku\"}".getBytes()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].title", is("iPhone 5s")));
    }

}
