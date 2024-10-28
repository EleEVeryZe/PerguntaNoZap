package com.demo.account.adapter.input.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.account.adapter.input.controllers.dto.AccountDTO;
import com.demo.account.adapter.input.controllers.dto.AnswerDTO;
import com.demo.account.domain.services.dto.BigBallDTO;
import com.demo.account.port.input.CreateBigBallUseCase;
import com.demo.account.port.input.LoginUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Deprecated
@Slf4j
@RestController
@RequestMapping("/bigball")
public class BigBallController {

    private List<BigBallDTO> bigBalls;
    private CreateBigBallUseCase createBigBallUseCase;
    private LoginUseCase loginUseCase;

    public BigBallController(CreateBigBallUseCase createBigBallUseCase, LoginUseCase loginUseCase) {
        this.createBigBallUseCase = createBigBallUseCase;
        this.loginUseCase = loginUseCase;
        bigBalls = new ArrayList<>();
    }

    @GetMapping("/next_question")
    public ResponseEntity<Object> getQuestion(@Valid @NotNull @NotEmpty String numberId) {
        return ResponseEntity.ok(createBigBallUseCase.getNextQuestion(numberId));
    }

    @PostMapping("/answer")
    public ResponseEntity<Object> answer(@RequestBody @Valid AnswerDTO answerDTO) {
        createBigBallUseCase.answer(answerDTO);
        return ResponseEntity.ok("Sucess");
    }

    @GetMapping("/account")
    public ResponseEntity<Object> login(@RequestBody AccountDTO entity) {
        var token = loginUseCase.execute(entity);
        return ResponseEntity.ok(token);
    }

}
