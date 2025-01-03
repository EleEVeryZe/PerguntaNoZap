package com.demo.account.domain.services;

import java.util.LinkedList;
import java.util.Queue;
import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.services.questions.GameResponse;

public class GameListService extends GameService {
    private Queue<GameResponse> gameQuestions = new LinkedList<>();

    public GameListService(String personId, GameEnum gameType, Queue<GameResponse> gameQuestions) {
        super(personId, gameType);
        this.personId = personId;
        this.gameQuestions = gameQuestions;
    }

    @Override
    protected GameResponse pollAskedResponse(int questionId) {
        return this.gameQuestions.poll();
    }

    @Override
    protected boolean isEmpty() {
        return gameQuestions.isEmpty();
    }

    @Override
    protected GameResponse getSpecificResponse() {
        return gameQuestions.peek().setQuestionsLeft(this.gameQuestions.size());
    }
}
