package com.demo.account.adapter.input.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.demo.account.domain.services.dto.admin.AdminQuestion;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BigBallControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @ParameterizedTest
        @ValueSource(strings = {"AtleticoXCruzeiro", "5", "23/04/2048", "19:30"})
        @DisplayName("Should answer all questions")
        void shouldAnswerAllQuestion(String value) throws Exception {
                var question = getQuestion("+12309123");

                this.mockMvc.perform(post("/bigball/answer")
                                .param("questionId", String.valueOf(question.id()))
                                .param("numberId", "+12309123").param("answer", value))
                                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Should throw erro if empty value passed to next_question")
        void shouldThrowErroIfEmptyValue() throws Exception {
                this.mockMvc.perform(get("/bigball/next_question").param("numberId", ""))
                                .andExpect(status().isUnprocessableEntity());
        }

        @Test
        @DisplayName("Should throw erro if passed invalid questionId")
        void shouldThrowErroIfInvalidQuestionIdPassed() throws Exception {
                this.mockMvc.perform(post("/bigball/answer").param("questionId", "321")
                                .param("numberId", "+55418499339").param("answer", "blablabla"))
                                .andExpect(status().isUnprocessableEntity())
                                .andExpect(jsonPath("$.errMsg")
                                                .value("Essa questão não foi perguntada"));
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "-5", "NOT_VALID_DATE", "NOT_VALID_HOUR"})
        @DisplayName("Should throw erro if passed valid questionId but value with incorrect format")
        void shouldThrowErroIfValuesWithIncorrectFormat(String value) throws Exception {
                var question = getQuestion("+55418499339");

                this.mockMvc.perform(post("/bigball/answer")
                                .param("questionId", String.valueOf(question.id()))
                                .param("numberId", "+55418499339").param("answer", value))
                                .andExpect(status().isBadRequest());
        }

        private AdminQuestion getQuestion(String numberId) throws Exception {
                var result = this.mockMvc
                                .perform(get("/bigball/next_question").param("numberId", numberId))
                                .andExpect(status().isOk()).andReturn();
                String json = result.getResponse().getContentAsString();
                return new ObjectMapper().readValue(json, AdminQuestion.class);
        }
}

