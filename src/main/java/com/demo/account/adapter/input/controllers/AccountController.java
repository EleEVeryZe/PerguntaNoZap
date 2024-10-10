package com.demo.account.adapter.input.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.account.adapter.input.controllers.dto.AccountDTO;
import com.demo.account.port.input.CreateAccountUseCase;
import com.demo.account.port.input.LoginUseCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AccountController {

    private CreateAccountUseCase createAccountUseCase;
    private LoginUseCase loginUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase, LoginUseCase loginUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/account")
    public ResponseEntity<Object> createAccount(@RequestBody AccountDTO entity) {
        createAccountUseCase.execute(entity);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/account")
    public ResponseEntity<Object> login(@RequestBody AccountDTO entity) {
        var token = loginUseCase.execute(entity);
        return ResponseEntity.ok(token);
    }
}
