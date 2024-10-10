package com.demo.account.src.main.domain.services.dto;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import com.demo.account.domain.exception.ParametroIncorretoException;
import com.demo.account.domain.services.dto.BigBallDTO;

@SpringBootTest
public class BigBallDTOTest {

    static BigBallDTO bigBallDTO;

    @BeforeAll
    static void init() {
        bigBallDTO = new BigBallDTO("+31984976488");
    }

    @ParameterizedTest
    @ValueSource(strings = {"registerBigBallValue", "setDate"})
    void registerBigBallValue(String functionName) {
        Object inputValue = null;
        Object value = null;
        switch (functionName) {
            case "setValue":
                inputValue = 5;
                value = bigBallDTO.setValue((double) inputValue);
                break;
            case "setDate":
                inputValue =
                        new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
                value = bigBallDTO.setDate((String) inputValue);
                break;
            case "setHour":
                inputValue = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
                value = bigBallDTO.setHour((String) inputValue);
                break;
            case "setTeams":
                inputValue = "AtleticoXCruzeiro";
                value = bigBallDTO.setTeams((String) inputValue);
                break;
        }
        assertThat(value, is(equalTo(inputValue)));
    }

    @Test
    @DisplayName("Should throw error on regisering value smaller than 0")
    void registerBigBallValueSmallerThan0() {
        double inputValue = -1;
        assertThrows(ParametroIncorretoException.class, () -> bigBallDTO.setValue(inputValue),
                "Valor do bolão não pode ser abaixo de 0");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "asdf", "208"})
    @DisplayName("Should throw error if date is not a valid value")
    void registerBigBallWithDateIncorrectValue(String values) {
        assertThrows(ParametroIncorretoException.class, () -> bigBallDTO.setDate(values),
                "Data não está com formato aceito. Considere o formato 27/02/1993");
    }

    @Test
    @DisplayName("Should throw error if date is previous than today")
    void registerBigBallWithDateBeforeToday() {
        String yesterdayDate = new SimpleDateFormat("dd/MM/yyyy").format(getYesterdayDate());
        assertThrows(ParametroIncorretoException.class, () -> bigBallDTO.setDate(yesterdayDate),
                "Data não deve ser anterior a hoje");
    }

    @ParameterizedTest
    @ValueSource(strings = {"02/28/2025", "27-02-2028", "2028/02/27"})
    @DisplayName("Should throw error if date is in wrong format")
    void registerBigBallWithDateIncorrectFormat(String incorrectDate) {
        assertThrows(ParametroIncorretoException.class, () -> bigBallDTO.setDate(incorrectDate),
                "Data não está com formato aceito. Considere o formato 27/02/1993");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "asdf", "208", "24:00", "12:234"})
    @DisplayName("Should throw error if time is incorrect")
    void registerBigBallWithTimeIncorrectValue(String values) {
        assertThrows(ParametroIncorretoException.class, () -> bigBallDTO.setHour(values),
                "Horas não está com formato aceito. Considere o formato 19:30");
    }

    @Test
    @DisplayName("Should throw error if team is null or empty")
    void registerBigBallWithTeamsEmptyOrNull() {
        assertThrows(ParametroIncorretoException.class, () -> bigBallDTO.setTeams(""),
                "É necessário definir os times");
        assertThrows(ParametroIncorretoException.class, () -> bigBallDTO.setTeams(null),
                "É necessário definir os times");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Quais os times do bolão? Ex de formato: AtleticoXPalmeiras",
            "Qual o valor do bolão? Apenas numeros", "Data do jogo? Ex de formato: 27/02/2048",
            "Qual o horário do jogo? Ex de formato: 19:00"})
    @DisplayName("Should get question and questionId")
    void shouldGetQuestionAndId(String expectedText) {
        var questionA = bigBallDTO.getQuestions().pollQuestion();
        assertThat(questionA.text(), equalTo(expectedText));
    }

    private Date getYesterdayDate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
