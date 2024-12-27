package com.demo.account.domain.services.questions;

import java.util.List;
import java.util.Optional;
import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.services.questions.verifiers.Verifier;

public abstract class GameResponse {
    protected String text;
    protected GameEnum type;
    protected String answer;
    protected final Optional<List<Verifier>> verifier;

    protected GameResponse(String text, GameEnum gameEnum, Verifier... verifier) {
        this.text = text;
        this.type = gameEnum;
        this.verifier = Optional.ofNullable(List.of(verifier));
    }

    public void answer(String answer) {
        if (this.verifier.isPresent())
            this.verifier.get().forEach(func -> func.verifyAnswer(answer));

        this.answer = answer;
    }

    public abstract String printAnswer();

    public GameEnum getType() {
        return this.type;
    }

    public String getText() {
        return text;
    }

}
