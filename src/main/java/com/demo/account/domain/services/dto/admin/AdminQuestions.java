package com.demo.account.domain.services.dto.admin;

import java.util.LinkedList;
import java.util.Queue;

public class AdminQuestions {
    Queue<AdminQuestion> questions = new LinkedList<>();

    public AdminQuestions() {
        start();
    }

    private void start() {
        questions.add(
                new AdminQuestion(0, "Quais os times do bolão? Ex de formato: AtleticoXPalmeiras"));
        questions.add(new AdminQuestion(1, "Qual o valor do bolão? Apenas numeros"));
        questions.add(new AdminQuestion(2, "Data do jogo? Ex de formato: 27/02/2048"));
        questions.add(new AdminQuestion(3, "Qual o horário do jogo? Ex de formato: 19:00"));
    }

    public AdminQuestion pollQuestion() {
        return questions.poll();
    }

    public int getQuestionsLeft() {
        return questions.size();
    }

    public void reStart() {
        start();
    }
}
