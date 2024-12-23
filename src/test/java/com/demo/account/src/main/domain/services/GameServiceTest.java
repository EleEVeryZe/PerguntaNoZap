package com.demo.account.src.main.domain.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.demo.account.domain.exception.ParametroIncorretoException;
import com.demo.account.domain.services.GameService;
import com.demo.account.domain.services.questions.QuestionBuilderImp;

public class GameServiceTest {

    private static GameService gameServiceSUT;

    @BeforeAll
    static void setUp() {
        gameServiceSUT =
                new GameService("+3198497648", QuestionBuilderImp.umJogo().doBicho().build());
    }

    @Test
    @DisplayName("Should get next question")
    void test() {
        assertThat(gameServiceSUT.getNextQuestion(), is(not(nullValue())));
    }

    @Test
    @DisplayName("Should answer existing question")
    void test2() {
        try {
            gameServiceSUT.answer(0, "27/04/2045");
        } catch (Exception ex) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    @DisplayName("Should throw error answering non-existing question")
    void test3() {
        int nonExistingQuestionId = -1;
        assertThrows(ParametroIncorretoException.class,
                () -> gameServiceSUT.answer(nonExistingQuestionId, "27/04/2045"));
    }

}
