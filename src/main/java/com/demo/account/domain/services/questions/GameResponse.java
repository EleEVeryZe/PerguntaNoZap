package com.demo.account.domain.services.questions;

import com.demo.account.domain.enums.GameEnum;

public class GameResponse {
    protected String text;
    protected GameEnum type;

    public GameResponse(String text, GameEnum gameEnum) {
        this.text = text;
        this.type = gameEnum;
    }
}
