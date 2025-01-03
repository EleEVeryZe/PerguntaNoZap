package com.demo.account.adapter.input.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.account.adapter.input.controllers.dto.AnswerDTO;
import com.demo.account.adapter.input.controllers.dto.GameResponseDTO;
import com.demo.account.domain.services.GamesServiceSingleton;

@RestController
public class GameController {

    @GetMapping("/question/{gameName}/{personId}")
    public ResponseEntity<GameResponseDTO> getQuestion(@PathVariable() String gameName,
            @PathVariable() String personId) {
        return ResponseEntity.ok(GamesServiceSingleton.startOrResume(personId, gameName)
                .getNextResponse().parseToDto());
    }

    @PostMapping("/answer/{gameName}/{personId}")
    public ResponseEntity<Object> answer(@PathVariable() String gameName,
            @PathVariable() String personId, @RequestBody AnswerDTO answer) {

        GamesServiceSingleton.resume(personId, gameName).answer(answer.questionId(), answer.text());

        return ResponseEntity.ok("");
    }
}
