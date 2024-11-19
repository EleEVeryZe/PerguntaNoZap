package com.demo.account.src.main.domain.services.questions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.exception.ParametroIncorretoException;
import com.demo.account.domain.services.questions.Question;
import com.demo.account.domain.services.questions.verifiers.DataFormatoBRVerifier;

@SpringBootTest
public class QuestionTest {

    private Question questionSUT;
    private DataFormatoBRVerifier formatadorDeDatas = new DataFormatoBRVerifier();

    @BeforeEach
    void setUp() {
        this.questionSUT = new Question(1, "Insira a data", GameEnum.BIG_BALL, formatadorDeDatas);
    }

    @Test
    @DisplayName("Should verify answer")
    void Should_verify_Answer() {
        String dataAnteriorAHoje = "19/02/1993";
        assertThrows(ParametroIncorretoException.class,
                () -> this.questionSUT.answer(dataAnteriorAHoje));

    }

}
