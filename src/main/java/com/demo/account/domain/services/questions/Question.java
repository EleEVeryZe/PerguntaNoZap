package com.demo.account.domain.services.questions;

import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.services.questions.verifiers.Verifier;

public class Question extends GameResponse {
    private int id;
    private final Verifier verifier;
    private String answer;

    public Question(int id, String text, GameEnum type, Verifier verifier) {
        super(text, type);
        this.id = id;
        this.verifier = verifier;
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

    public String getQuestionWithAnswers() {
        return this.text + " --> " + this.answer;
    }
}
