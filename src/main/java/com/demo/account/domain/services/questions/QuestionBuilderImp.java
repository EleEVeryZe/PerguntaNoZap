package com.demo.account.domain.services.questions;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import com.demo.account.domain.enums.GameEnum;

public class QuestionBuilderImp implements QuestionBuilder<Queue<GameResponse>> {
    Queue<GameResponse> questions = new LinkedList<>();

    public static QuestionBuilderImp umJogo() {
        return new QuestionBuilderImp();
    }

    public QuestionBuilderImp doBicho() {
        questions.add(new Question(0,
                "Voce agora está no jogo do bicho. Por favor, escolha qual animal voce irá botar sua fezinha",
                null));
        questions.add(new Question(0, "Qual o valor da sua fézinha?", null));
        return this;
    }

    public QuestionBuilderImp doBolao() {
        questions.add(new Question(1,
                "Olá, seja bem vindo ao ISABET. Atlético e River vão disputar a semi final da libertadores. Deseja fazer uma fezinha?",
                Optional.empty()));
        questions.add(new Question(2, "Quantos gols o atlético vai fazer?", Optional.empty()));
        questions.add(new Question(3, "e quantos gols o River vai fazer?", Optional.empty()));
        questions.add(new Question(4,
                "Essa aqui é a nossa chave pix. O valor do bolão é de R$5 -> chavepixdaisa",
                Optional.empty()));
        return this;
    }


    public QuestionBuilderImp deRamal() {
        questions.add(new Question(1, "Por favor, digite o seu nome...", Optional.empty()));

        questions.add(new QuestionTree(2, "Digite -> 1. Financeiro 2. SAC",
                List.of(new QuestionTree(1, "1. Enviar Segunda Via 2. Atualizar Dados Bancários",
                        null, Optional.empty()),
                        new QuestionTree(2, "1. Fazer uma reclamação 2. Fazer um elogio", null,
                                Optional.empty())),
                Optional.empty()

        ));

        return this;
    }

    public QuestionBuilderImp doTipo(GameEnum gameType) {
        switch (gameType) {
            case BIG_BALL:
                return doBolao();
            case RAMAL:
                return deRamal();
            default:
                return null;
        }
    }

    @Override
    public Queue<GameResponse> build() {
        return questions;
    }

}
