package com.demo.account.adapter.input.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.demo.account.adapter.input.controllers.dto.AnswerDTO;
import com.demo.account.adapter.input.controllers.dto.GameResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        this.mockMvc.perform(post("/answer/" + gameName + "/" + personId)
                .param("answer", "27/02/2048").param("questionId", "0")).andReturn();
    }

    @Test
    @DisplayName("Should fail call GET question due to empty fields")
    void test2() throws Exception {
        String gameName = "";
        String personId = "";

        this.mockMvc.perform(get("/question/" + gameName + "/" + personId))
                .andExpect(status().isNotFound());
    }

    GameResponseDTO getNextQuestion() throws Exception {
        var rt = this.mockMvc.perform(get("/question/" + gameName + "/" + personId)).andReturn();
        String json = rt.getResponse().getContentAsString();
        return new ObjectMapper().readValue(json, GameResponseDTO.class);
    }

    void answer(GameResponseDTO question) throws Exception {
        AnswerDTO answer = new AnswerDTO(question.id(), "Answer to: " + question.text());
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(answer);

        this.mockMvc.perform(post("/answer/" + gameName + "/" + personId)
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
    }

    @Test
    @DisplayName("Should behave correctly")
    void shouldBehaveAsExpected() throws Exception {
        var question1 = getNextQuestion();
        assertThat(question1, is(not(nullValue())));
        answer(question1);

        GameResponseDTO currentResponse = null;
        GameResponseDTO previous = question1;
        int i = 0;
        while (i < question1.questionsLeft() - 1) {
            currentResponse = getNextQuestion();
            assertThat(currentResponse.text(), is(not(equalTo(previous.text()))));
            answer(currentResponse);
            previous = currentResponse;
            i++;
        }

        var summary = getNextQuestion();
        assertThat(summary.text(), is(not(equalTo(currentResponse.text()))));
        answer(summary);
    }
}
