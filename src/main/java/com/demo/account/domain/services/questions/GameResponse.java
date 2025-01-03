package com.demo.account.domain.services.questions;

import java.util.List;
import java.util.Optional;
import com.demo.account.adapter.input.controllers.dto.GameResponseDTO;
import com.demo.account.domain.services.questions.verifiers.Verifier;

public abstract class GameResponse {
    protected String text;
    protected String answer;
    protected final Optional<List<Verifier>> verifier;
    protected int id;
    protected int questionsLeft;

    protected GameResponse(int id, String text, Optional<List<Verifier>> verifier) {
        this.text = text;
        this.verifier = verifier;
        this.id = id;
    }

    public GameResponse answer(String answer) {
        if (this.verifier.isPresent())
            this.verifier.get().forEach(func -> func.verifyAnswer(answer));

        this.answer = answer;
        return this;
    }

    public GameResponseDTO parseToDto() {
        return new GameResponseDTO(text, id, questionsLeft);
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return this.answer;
    }

    public int getId() {
        return id;
    }

    public GameResponse setQuestionsLeft(int questionsLeft) {
        this.questionsLeft = questionsLeft;
        return this;
    }

    public abstract String printAnswer();
}
