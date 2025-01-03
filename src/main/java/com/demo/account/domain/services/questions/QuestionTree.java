package com.demo.account.domain.services.questions;

import java.util.List;
import java.util.Optional;
import com.demo.account.domain.services.questions.verifiers.Verifier;

public class QuestionTree extends GameResponse {
    private List<QuestionTree> options;

    public QuestionTree(int id, String summary, List<QuestionTree> options,
            Optional<List<Verifier>> verifier) {
        super(id, summary, verifier);
        this.options = options;
    }

    @Override
    public String printAnswer() {
        return this.text + " --> " + this.answer;
    }

    public QuestionTree getSelectedOption(int id) {
        return this.options.stream().filter(opt -> opt.getId() == id).findFirst().orElse(null);
    }

    public List<QuestionTree> getOptions() {
        return this.options;
    }
}
