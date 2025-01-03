package com.demo.account.domain.services.questions;

import java.util.List;
import java.util.Optional;
import com.demo.account.domain.services.questions.verifiers.Verifier;

public class Question extends GameResponse {
    public Question(int id, String summary, Optional<List<Verifier>> verifier) {
        super(id, summary, verifier);
    }

    @Override
    public String printAnswer() {
        return this.text + " --> " + this.answer;
    }

}
