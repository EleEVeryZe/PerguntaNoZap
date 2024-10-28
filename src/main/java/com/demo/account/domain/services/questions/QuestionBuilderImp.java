package com.demo.account.domain.services.questions;

import java.util.LinkedList;
import java.util.Queue;
import com.demo.account.domain.enums.GameEnum;

public class QuestionBuilderImp implements QuestionBuilder<Queue<Question>> {
    Queue<Question> questions = new LinkedList<>();

    public static QuestionBuilderImp umJogo() {
        return new QuestionBuilderImp();
    }

    public QuestionBuilderImp doBicho() {
        questions.add(new Question(0,
                "Voce agora está no jogo do bicho. Por favor, escolha qual animal voce irá botar sua fezinha",
                GameEnum.BIG_BALL, null));
        questions.add(new Question(0, "Qual o valor da sua fézinha?", GameEnum.BIG_BALL, null));
        return this;
    }

    public QuestionBuilderImp doBolao() {
        questions.add(new Question(1,
                "Olá, seja bem vindo ao ISABET. Atlético e River vão disputar a semi final da libertadores. Deseja fazer uma fezinha?",
                GameEnum.BIG_BALL, null));
        questions.add(
                new Question(2, "Quantos gols o atlético vai fazer?", GameEnum.BIG_BALL, null));
        questions
                .add(new Question(3, "e quantos gols o River vai fazer?", GameEnum.BIG_BALL, null));
        questions.add(new Question(4,
                "Essa aqui é a nossa chave pix. O valor do bolão é de R$5 -> chavepixdaisa",
                GameEnum.BIG_BALL, null));
        return this;
    }

    public QuestionBuilderImp doTipo(GameEnum gameType) {
        switch (gameType) {
            case BIG_BALL:
                return doBolao();
            default:
                return null;
        }
    }

    @Override
    public Queue<Question> build() {
        return questions;
    }

}
