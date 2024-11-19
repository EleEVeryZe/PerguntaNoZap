package com.demo.account.domain.services.questions.verifiers;

import java.util.List;
import org.springframework.http.HttpStatus;
import com.demo.account.domain.exception.ParametroIncorretoException;

public class MenuOptionVerifier implements Verifier {

    private List<String> options;

    public MenuOptionVerifier(List<String> options) {
        this.options = options;
    }

    @Override
    public void verifyAnswer(String option) {
        if (!this.options.contains(option))
            throw new ParametroIncorretoException("Opção incorreta. Tente novamente.",
                    HttpStatus.BAD_REQUEST);
    }

}
