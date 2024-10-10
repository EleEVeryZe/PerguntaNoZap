package com.demo.account.adapter.input.controllers.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDTO {
    @Nonnull
    private String name;

    @Nonnull
    private String email;

    @Size(min = 4)
    private String password;

    @Nonnull
    private String confirmPassword;

    @Nonnull
    private boolean isActive;
}
