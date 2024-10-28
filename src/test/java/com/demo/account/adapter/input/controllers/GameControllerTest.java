package com.demo.account.adapter.input.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {
    String gameName = "BIG_BALL";
    String personId = "+5531984648877";

    @Autowired
    private MockMvc mockMvc;

    void createGame() throws Exception {
        this.mockMvc.perform(get("/question/" + gameName + "/" + personId))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.text").exists());
    }

    @Test
    @DisplayName("Should call GET question")
    void test1() throws Exception {
        createGame();
    }

    @Test
    @DisplayName("Should call POST answer")
    void test3() throws Exception {
        createGame();

        var aux =
                this.mockMvc
                        .perform(post("/answer/" + gameName + "/" + personId)
                                .param("answer", "27/02/2048").param("questionId", "0"))
                        .andReturn();


    }

    @Test
    @DisplayName("Should fail call GET question")
    void test2() throws Exception {
        String gameName = "";
        String personId = "";

        this.mockMvc.perform(get("/question/" + gameName + "/" + personId))
                .andExpect(status().isNotFound());
    }
}
