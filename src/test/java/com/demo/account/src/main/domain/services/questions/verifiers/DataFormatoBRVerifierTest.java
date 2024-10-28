package com.demo.account.src.main.domain.services.questions.verifiers;

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
import com.demo.account.domain.services.questions.verifiers.DataFormatoBRVerifier;

@SpringBootTest
public class DataFormatoBRVerifierTest {

    private static DataFormatoBRVerifier dataFormatoBRVerifierSUT;

    @BeforeAll
    static void setUp() {
        dataFormatoBRVerifierSUT = new DataFormatoBRVerifier();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "asdf", "208"})
    @DisplayName("Should throw error if date is not a valid value")
    void registerBigBallWithDateIncorrectValue(String values) {
        assertThrows(ParametroIncorretoException.class,
                () -> dataFormatoBRVerifierSUT.verifyAnswer(values),
                "Data não está com formato aceito. Considere o formato 27/02/1993");
    }

    @Test
    @DisplayName("Should throw error if date is previous than today")
    void registerBigBallWithDateBeforeToday() {
        String yesterdayDate = new SimpleDateFormat("dd/MM/yyyy").format(getYesterdayDate());
        assertThrows(ParametroIncorretoException.class,
                () -> dataFormatoBRVerifierSUT.verifyAnswer(yesterdayDate),
                "Data não deve ser anterior a hoje");
    }

    @ParameterizedTest
    @ValueSource(strings = {"02/28/2025", "27-02-2028", "2028/02/27"})
    @DisplayName("Should throw error if date is in wrong format")
    void registerBigBallWithDateIncorrectFormat(String incorrectDate) {
        assertThrows(ParametroIncorretoException.class,
                () -> dataFormatoBRVerifierSUT.verifyAnswer(incorrectDate),
                "Data não está com formato aceito. Considere o formato 27/02/1993");
    }

    private Date getYesterdayDate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
