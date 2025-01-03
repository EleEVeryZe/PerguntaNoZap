package com.demo.account.domain.enums;

public enum GameEnum {
    BIG_BALL("BIG_BALL"), HAMBURGUERIA("HAMBURGERIA"), RAMAL("RAMAL");

    private String gameType;

    GameEnum(String gameType) {
        this.gameType = gameType;
    }

    public String getType() {
        return this.gameType;
    }
}
