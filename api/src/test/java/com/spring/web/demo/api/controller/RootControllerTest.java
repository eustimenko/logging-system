package com.spring.web.demo.api.controller;

import com.fasterxml.jackson.databind.*;
import com.spring.web.demo.api.*;
import com.spring.web.demo.persistent.entity.User;
import com.spring.web.demo.persistent.repository.UserRepository;
import org.json.JSONArray;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApiTestConfiguration.class, Application.class})
@Ignore
public class RootControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        mvc = webAppContextSetup(context)
                .build();
    }

    @Test
    public void delete_group() throws Exception {
        final List<User> users = new ArrayList<>(10000);
        for (int i = 0; i < 100000; i++) {
            final User content = new User();
            content.setEmail(i + "ustimenko@ustimenko.com");
            content.setLogin(i + "ustimenko");
            content.setPassword("Admin");
            content.setFullname("Eugene Ustimenko");

            users.add(content);
        }
        repository.save(users);

        final Set<Long> ids = new HashSet<>(10000);
        for (long i = 3; i < 10000; i++) {
            ids.add(i);
        }

        mvc.perform(delete("/api/root")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new JSONArray(ids).toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_single() throws Exception {
        mvc.perform(delete("/api/root/{id}", 2))
                .andExpect(status().isOk());
    }

    @Test
    public void list() throws Exception {
        mvc.perform(get("/api/root"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))));
    }

    @Test
    public void update() throws Exception {
        final User content = new User();
        content.setEmail("rcu@ustimenko.com");
        content.setLogin("rcu");
        content.setFullname("Eugene Ustimenko");

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mvc.perform(put("/api/root/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(writer.writeValueAsBytes(content)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is("rcu")));
    }
}
