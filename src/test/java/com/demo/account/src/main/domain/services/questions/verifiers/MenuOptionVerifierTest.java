package com.demo.account.src.main.domain.services.questions.verifiers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.demo.account.domain.exception.ParametroIncorretoException;
import com.demo.account.domain.services.questions.verifiers.MenuOptionVerifier;

@SpringBootTest
public class MenuOptionVerifierTest {

    private MenuOptionVerifier menuOptionsVerifierSUT =
            new MenuOptionVerifier(List.of("1", "2", "3", "4"));

    @Test
    @DisplayName("Should validate correctly if SUT verifies correctly user answer")
    void shouldValidate() {
        menuOptionsVerifierSUT.verifyAnswer("1");
        menuOptionsVerifierSUT.verifyAnswer("2");
        menuOptionsVerifierSUT.verifyAnswer("3");
        menuOptionsVerifierSUT.verifyAnswer("4");

        assertThrows(ParametroIncorretoException.class,
                () -> menuOptionsVerifierSUT.verifyAnswer("56"),
                "Opção incorreta. Tente novamente.");
    }

}
