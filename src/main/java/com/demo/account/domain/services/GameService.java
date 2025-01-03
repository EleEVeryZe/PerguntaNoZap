package com.demo.account.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.services.questions.GameResponse;
import com.demo.account.domain.services.questions.Summary;

public abstract class GameService {
    protected String personId;
    protected GameEnum gameType;
    protected List<GameResponse> answeredResponses = new ArrayList();

    protected GameService(String personId, GameEnum gameType) {
        this.personId = personId;
        this.gameType = gameType;
    }

    public GameService answer(int questionId, String answer) {
        var isNotEmpty = !isEmpty();
        if (isNotEmpty)
            answeredResponses.add(pollAskedResponse(questionId).answer(answer));
        return this;
    }

    public GameResponse getNextResponse() {
        var isEmpty = isEmpty();
        if (isEmpty) {
            String summary = this.answeredResponses.stream().map(q -> q.printAnswer())
                    .collect(Collectors.joining(" , "));
            return new Summary(-1, summary, null);
        }

        return getSpecificResponse();
    }

    public String getPersonId() {
        return personId;
    }

    protected abstract GameResponse pollAskedResponse(int questionId);

    protected abstract boolean isEmpty();

    protected abstract GameResponse getSpecificResponse();
}
