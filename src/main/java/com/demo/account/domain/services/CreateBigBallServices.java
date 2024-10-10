package com.demo.account.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.demo.account.adapter.input.controllers.dto.AnswerDTO;
import com.demo.account.domain.exception.ParametroIncorretoException;
import com.demo.account.domain.mongo.BigBallMongoRepository;
import com.demo.account.domain.mongo.entities.BigBall;
import com.demo.account.domain.services.dto.BigBallDTO;
import com.demo.account.domain.services.dto.admin.AdminQuestion;
import com.demo.account.port.input.CreateBigBallUseCase;

@Service
public class CreateBigBallServices implements CreateBigBallUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBigBallServices.class);
    BigBallMongoRepository bigBallMongo;

    private List<BigBallDTO> bigBalls;

    public CreateBigBallServices(BigBallMongoRepository bigBallMongo) {
        this.bigBallMongo = bigBallMongo;
        this.bigBalls = new ArrayList<>();
    }

    public void saveBigBall(BigBallDTO bigBall) {
        LOGGER.info("Start: Creating BigBall");

        bigBallMongo.save(
                new BigBall(UUID.randomUUID().toString(), bigBall.getValue(), bigBall.getDate(),
                        bigBall.getTeams().split("X"), bigBall.getHour(), bigBall.getNumberId()));

        LOGGER.info("End: Creating BigBall");
    }

    public AdminQuestion getNextQuestion(String numberId) {
        BigBallDTO innerBigBall = getBigBallByNumberId(numberId);

        if (innerBigBall == null) {
            innerBigBall = new BigBallDTO(numberId);
            bigBalls.add(innerBigBall);
        }

        if (innerBigBall.getQuestions().getQuestionsLeft() == 0) {
            saveBigBall(innerBigBall);
            innerBigBall.getQuestions().reStart();
            return null;
        }

        return innerBigBall.getQuestions().pollQuestion();
    }

    public void answer(AnswerDTO answerDTO) {
        BigBallDTO innerBigBall = getBigBallByNumberId(answerDTO.numberId());

        if (innerBigBall == null)
            throw new ParametroIncorretoException("Essa questão não foi perguntada",
                    HttpStatus.UNPROCESSABLE_ENTITY);

        innerBigBall.answerQuestion(answerDTO.questionId(), answerDTO.answer());
    }

    private BigBallDTO getBigBallByNumberId(String numberId) {
        return bigBalls.stream().filter(item -> item.getNumberId().equals(numberId)).findFirst()
                .orElse(null);
    }
}
