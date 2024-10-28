package com.demo.account.adapter.input.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.account.adapter.input.controllers.dto.AnswerDTO;
import com.demo.account.domain.exception.ParametroIncorretoException;
import com.demo.account.domain.services.GameService;

@RestController
public class GameController {

    List<GameService> gameServices;

    public GameController() {
        this.gameServices = new ArrayList<>();
    }

    @GetMapping("/question/{gameName}/{personId}")
    public ResponseEntity<Object> getQuestion(@PathVariable(name = "gameName") String gameName,
            @PathVariable(name = "personId") String personId) {

        GameService personQuestions =
                GameService.findGameQuestionByPersonId(this.gameServices, personId);

        if (personQuestions == null) {
            personQuestions = GameService.newGame(gameName, personId);
            this.gameServices.add(personQuestions);
        }

        return ResponseEntity.ok(personQuestions.getNextQuestion());
    }

    @PostMapping("/answer/{gameName}/{personId}")
    public ResponseEntity<Object> answer(@PathVariable(name = "gameName") String gameName,
            @PathVariable(name = "personId") String personId, @RequestBody AnswerDTO answer) {

        GameService personQuestions =
                GameService.findGameQuestionByPersonId(this.gameServices, personId);

        if (personQuestions == null)
            throw new ParametroIncorretoException("Voce ainda não iniciou o jogo",
                    HttpStatus.BAD_REQUEST);

        personQuestions.answerQuestion(answer.questionId(), answer.answer());

        return ResponseEntity.ok("");
    }
}
