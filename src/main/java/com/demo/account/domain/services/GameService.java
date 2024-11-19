package com.demo.account.domain.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.springframework.http.HttpStatus;
import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.exception.ParametroIncorretoException;
import com.demo.account.domain.services.questions.Question;
import com.demo.account.domain.services.questions.QuestionBuilderImp;

public class GameService {
    private String personId;

    private Queue<Question> gameQuestions = new LinkedList<>();
    private List<Question> answeredQuestions = new ArrayList();

    public GameService(String personId, Queue<Question> gameQuestions) {
        this.personId = personId;
        this.gameQuestions = gameQuestions;
    }

    public Question getNextQuestion() {
        return gameQuestions.peek();
    }

    public void answerQuestion(int questionId, String answer) {
        var questionToBeAnswered = this.gameQuestions.stream()
                .filter(question -> question.getId() == questionId).findFirst().orElse(null);

        if (questionToBeAnswered == null)
            throw new ParametroIncorretoException("Essa pergunta não foi perguntada",
                    HttpStatus.BAD_REQUEST);

        questionToBeAnswered.answer(answer);

        answeredQuestions.add(gameQuestions.poll());
    }

    public String getPersonId() {
        return personId;
    }

    public static GameService newGame(String gameName, String personId) {
        return new GameService(personId,
                QuestionBuilderImp.umJogo().doTipo(GameEnum.valueOf(gameName)).build());
    }

    public static GameService findGameQuestionByPersonId(List<GameService> gameServices,
            String personId) {
        return gameServices.stream().filter(game -> game.getPersonId().equals(personId)).findFirst()
                .orElse(null);
    }
}
