package com.demo.account.src.main.domain.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.demo.account.domain.mongo.BigBallMongoRepository;
import com.demo.account.domain.mongo.entities.BigBall;
import com.demo.account.domain.services.CreateBigBallServices;
import com.demo.account.domain.services.dto.BigBallDTO;

@SpringBootTest
public class CreateBigBallServicesTest {
    @Mock
    BigBallMongoRepository bigBallMongo;

    CreateBigBallServices createBigBallServices;

    @BeforeEach
    void setUp() {
        createBigBallServices = new CreateBigBallServices(bigBallMongo);
    }

    @Test
    @DisplayName("Should store BigBall on Database")
    void shouldStoreBigBallOnDB() {
        BigBallDTO bigBall = new BigBallDTO(5.0, "", "ATLETICOXCRUZEIRO", "", "");
        createBigBallServices.saveBigBall(bigBall);
        verify(bigBallMongo).save(new BigBall(any(), bigBall.getValue(), bigBall.getDate(),
                bigBall.getTeams().split("X"), bigBall.getHour(), bigBall.getNumberId()));
    }
}
