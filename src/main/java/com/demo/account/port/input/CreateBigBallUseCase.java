package com.demo.account.port.input;

import com.demo.account.adapter.input.controllers.dto.AnswerDTO;
import com.demo.account.domain.services.dto.BigBallDTO;
import com.demo.account.domain.services.dto.admin.AdminQuestion;

public interface CreateBigBallUseCase {
    public void saveBigBall(BigBallDTO bigBall);

    public AdminQuestion getNextQuestion(String numberId);

    public void answer(AnswerDTO answerDTO);
}
