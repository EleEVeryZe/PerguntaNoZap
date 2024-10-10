package com.demo.account.domain.services;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.account.adapter.input.controllers.dto.AccountDTO;
import com.demo.account.domain.exception.UserRegistrationException;
import com.demo.account.domain.mongo.AccountServiceMongo;
import com.demo.account.domain.mongo.entities.AccountMongo;
import com.demo.account.port.input.CreateAccountUseCase;
import com.demo.account.repositories.CRUDRepository;

@Service
public class CreateAccountService implements CreateAccountUseCase {
    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountService.class);
    @Autowired
    CRUDRepository accountRepository;

    @Autowired
    AccountServiceMongo accountServiceMongo;

    @Override
    public void execute(AccountDTO account) {
        LOGGER.info("Start: Creating Account");
        validateAccount(account);
        var newUser = saveUser(account);
        LOGGER.info("END: Creating Account with ", newUser);
    }

    private AccountMongo saveUser(AccountDTO account) {
        var hashPassword = PASSWORD_ENCODER.encode(account.getPassword());
        var newUser = new AccountMongo(UUID.randomUUID().toString(), account.getEmail(),
                account.getName(), hashPassword, true);
        return accountServiceMongo.save(newUser);
    }

    private void validateAccount(AccountDTO account) throws UserRegistrationException {
        boolean doesUserExists = !accountServiceMongo.findByEmail(account.getEmail()).isEmpty();
        if (doesUserExists)
            throw new UserRegistrationException("Usuário já cadastrado", HttpStatus.CONFLICT);

        if (!account.getPassword().equals(account.getConfirmPassword()))
            throw new UserRegistrationException("Campo Senha e Confirmar Senha não são iguais",
                    HttpStatus.FORBIDDEN);
    }

}
