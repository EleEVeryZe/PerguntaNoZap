package com.demo.account.domain.services.questions;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.services.questions.verifiers.MenuOptionVerifier;

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

    private QuestionBuilderImp getHamburgueria() {
        questions.add(new Question(1,
                "Olá, seja bem vindo a Hamburgeria Sabor Saudável. O que gostaria de comer? \n1. X-Tudo R$21,4\n2. X-Bacon R$15,4\n3. X-Egg R$30,3\nObs: Fotos dos itens no cardápio",
                GameEnum.BIG_BALL, new MenuOptionVerifier(List.of("1", "2", "3"))));
        questions.add(new Question(2,
                "E pra beber, o que você gostaria? \n1. Latinha de coca-cola 358ml R$5\n2. Guaraná Antartica 1 Litro R$5,5",
                GameEnum.BIG_BALL, new MenuOptionVerifier(List.of("1", "2"))));
        questions
                .add(new Question(2, "Digite o nome e número da sua rua", GameEnum.BIG_BALL, null));
        questions.add(new Question(2, "Digite a cidade e bairro", GameEnum.BIG_BALL, null));
        return this;
    }

    public QuestionBuilderImp doTipo(GameEnum gameType) {
        switch (gameType) {
            case BIG_BALL:
                return doBolao();
            case HAMBURGUERIA:
                return getHamburgueria();
            default:
                return null;
        }
    }

    @Override
    public Queue<Question> build() {
        return questions;
    }

}
