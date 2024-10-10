package com.demo.account.domain.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.account.adapter.input.controllers.dto.AccountDTO;
import com.demo.account.domain.exception.UserRegistrationException;
import com.demo.account.domain.mongo.AccountServiceMongo;
import com.demo.account.domain.utils.JWTUtils;
import com.demo.account.port.input.LoginUseCase;

@Service
public class LoginService implements LoginUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountService.class);
    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    AccountServiceMongo accountServiceMongo;

    @Override
    public String execute(AccountDTO userDto) {
        LOGGER.info("Start: Login User");
        validateLogin(userDto);
        var token = JWTUtils.generateJwtToken(7);
        LOGGER.info("End: Login User");
        return token;
    }

    private void validateLogin(AccountDTO userDto) {
        var userOnDb = accountServiceMongo.findByEmail(userDto.getEmail()).stream().findFirst();

        if (userOnDb.isEmpty()
                && !PASSWORD_ENCODER.matches(userDto.getPassword(), userOnDb.get().getPassword()))
            throw new UserRegistrationException("Usuário ou senha inválidos", HttpStatus.FORBIDDEN);
    }
}
