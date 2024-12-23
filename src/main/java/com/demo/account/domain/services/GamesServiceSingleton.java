package com.demo.account.domain.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.exception.ParametroIncorretoException;
import com.demo.account.domain.services.questions.QuestionBuilderImp;

public class GamesServiceSingleton {
    static List<GameService> gameServices = new ArrayList<>();

    public static GameService resume(String personId, String gameName) {
        var personGame = findGameQuestionByPersonId(gameServices, personId);

        if (personGame == null)
            throw new ParametroIncorretoException("Voce ainda não iniciou o jogo",
                    HttpStatus.BAD_REQUEST);

        return personGame;
    }

    public static GameService start(String personId, String gameName) {
        GameService personQuestions = findGameQuestionByPersonId(gameServices, personId);

        if (personQuestions == null) {
            personQuestions = newGame(gameName, personId);
            gameServices.add(personQuestions);
        }

        return personQuestions;
    }

    private static GameService newGame(String gameName, String personId) {
        return new GameService(personId,
                QuestionBuilderImp.umJogo().doTipo(GameEnum.valueOf(gameName)).build());
    }

    public static GameService findGameQuestionByPersonId(List<GameService> gameServices,
            String personId) {
        return gameServices.stream().filter(game -> game.getPersonId().equals(personId)).findFirst()
                .orElse(null);
    }

}