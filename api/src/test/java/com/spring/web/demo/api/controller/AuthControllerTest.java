package com.spring.web.demo.api.controller;

import com.fasterxml.jackson.databind.*;
import com.spring.web.demo.api.*;
import com.spring.web.demo.logic.dto.UserDto;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApiTestConfiguration.class, Application.class})
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = webAppContextSetup(context)
                .build();
    }

    @Test
    public void register_success() throws Exception {
        final UserDto content = new UserDto();
        content.setEmail("ustimenko@ustimenko.com");
        content.setLogin("ustimenko");
        content.setPassword("Admin");
        content.setMatchingPassword("Admin");
        content.setFullname("Eugene Ustimenko");

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(writer.writeValueAsBytes(content)))
                .andExpect(status().isOk());
    }

    @Test
    public void register_duplicate_email_error() throws Exception {
        final UserDto content = new UserDto();
        content.setEmail("admin@admin.com");
        content.setLogin("ustimenko_admin");
        content.setPassword("Admin");
        content.setMatchingPassword("Admin");
        content.setFullname("Eugene Ustimenko");

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(writer.writeValueAsBytes(content)))
                .andExpect(status().isImUsed());
    }

    @Test
    public void register_duplicate_login_error() throws Exception {
        final UserDto content = new UserDto();
        content.setEmail("ustimenko_user@ustimenko.com");
        content.setLogin("admin");
        content.setPassword("Admin");
        content.setMatchingPassword("Admin");
        content.setFullname("Eugene Ustimenko");

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(writer.writeValueAsBytes(content)))
                .andExpect(status().isImUsed());
    }

    @Test
    public void register_validate_password_error() throws Exception {
        final UserDto content = new UserDto();
        content.setEmail("petrov@ustimenko.com");
        content.setLogin("petrov");
        content.setPassword("admin");
        content.setMatchingPassword("admin");
        content.setFullname("Eugene Petrov");

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(writer.writeValueAsBytes(content)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void register_matching_password_error() throws Exception {
        final UserDto content = new UserDto();
        content.setEmail("sidorov@ustimenko.com");
        content.setLogin("sidorov");
        content.setPassword("AdminSDF");
        content.setMatchingPassword("");
        content.setFullname("Eugene Sidorov");

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(writer.writeValueAsBytes(content)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void register_validate_email_error() throws Exception {
        final UserDto content = new UserDto();
        content.setEmail("petrov");
        content.setLogin("petrov_user");
        content.setPassword("Admin");
        content.setMatchingPassword("Admin");
        content.setFullname("Eugene Petrov");

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(writer.writeValueAsBytes(content)))
                .andExpect(status().isBadRequest());
    }
}
