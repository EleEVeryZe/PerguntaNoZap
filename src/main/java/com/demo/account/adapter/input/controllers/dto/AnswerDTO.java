package com.demo.account.adapter.input.controllers.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AnswerDTO(@Valid @NotNull @NotEmpty String numberId, int questionId,
        @Valid @NotNull @NotEmpty String answer) {
}
