package com.demo.account.domain.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.exception.ParametroIncorretoException;
import com.demo.account.domain.services.questions.GameResponse;
import com.demo.account.domain.services.questions.Question;

public class GameService {
    private String personId;
    private Queue<Question> gameQuestions = new LinkedList<>();
    private List<Question> answeredQuestions = new ArrayList();

    public GameService(String personId, Queue<Question> gameQuestions) {
        this.personId = personId;
        this.gameQuestions = gameQuestions;
    }

    public GameResponse getNextQuestion() {
        return gameQuestions.peek();
    }

    public void registerSummary() {
        if (this.gameQuestions.isEmpty()) {
            String summary = this.answeredQuestions.stream().map(q -> q.printAnswer())
                    .collect(Collectors.joining(" , "));
            gameQuestions.add(new Question(-1, summary, GameEnum.BIG_BALL, null));
        }
    }

    public GameService answer(int questionId, String answer) {
        var questionToBeAnswered = findQuestionById(questionId);

        if (questionToBeAnswered == null)
            throw new ParametroIncorretoException("Essa pergunta não foi perguntada",
                    HttpStatus.BAD_REQUEST);

        questionToBeAnswered.answer(answer);
        answeredQuestions.add(gameQuestions.poll());

        return this;
    }

    public String getPersonId() {
        return personId;
    }

    private Question findQuestionById(int questionId) {
        return this.gameQuestions.stream().filter(question -> question.getId() == questionId)
                .findFirst().orElse(null);
    }
}
