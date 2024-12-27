package com.demo.account.domain.services.questions;

import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.services.questions.verifiers.Verifier;

public class Question extends GameResponse {
    private int id;

    public Question(int id, String summary, GameEnum type, Verifier... verifier) {
        super(summary, type, verifier);
        this.id = id;
    }

    @Override
    public String printAnswer() {
        return this.text + " --> " + this.answer;
    }

    public int getId() {
        return id;
    }
}
