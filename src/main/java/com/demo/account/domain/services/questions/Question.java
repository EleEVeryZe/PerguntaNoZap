package com.demo.account.domain.services.questions;

import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.services.questions.verifiers.Verifier;

public class Question {
    private int id;
    private String text;
    private GameEnum type;
    private final Verifier verifier;
    private String answer;

    public Question(int id, String text, GameEnum type, Verifier verifier) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.verifier = verifier; // TODO: ser possível adicionar mais de um verifier
    }

    public void answer(String answer) {
        if (existsVerifier())
            this.verifier.verifyAnswer(answer);

        this.answer = answer;
    }

    public GameEnum getType() {
        return this.type;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    private boolean existsVerifier() {
        return this.verifier != null;
    }
}
