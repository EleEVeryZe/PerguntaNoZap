package com.demo.account.port.input;

import com.demo.account.adapter.input.controllers.dto.AccountDTO;

public interface CreateAccountUseCase {
    void execute(AccountDTO account);
}
