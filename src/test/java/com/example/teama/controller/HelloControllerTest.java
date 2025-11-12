package com.example.teama.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(HelloController.class)
@ActiveProfiles("test")
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", is("team-a-service")))
                .andExpect(jsonPath("$.status", is("healthy")))
                .andExpect(jsonPath("$.message", is("Welcome to Team A Service!")));
    }

    @Test
    void testHello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", is("team-a-service")))
                .andExpect(jsonPath("$.message", is("Hello from Team A Service!")));
    }

    @Test
    void testHelloWithName() throws Exception {
        mockMvc.perform(get("/hello/GitOps"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", is("team-a-service")))
                .andExpect(jsonPath("$.name", is("GitOps")))
                .andExpect(jsonPath("$.message", is("Hello GitOps from Team A Service!")));
    }

    @Test
    void testInfo() throws Exception {
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", is("team-a-service")))
                .andExpect(jsonPath("$.team", is("Team A")))
                .andExpect(jsonPath("$.description", is("Team A microservice for GitOps POC")));
    }

    @Test
    void testHealth() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("UP")))
                .andExpect(jsonPath("$.service", is("team-a-service")));
    }
}
