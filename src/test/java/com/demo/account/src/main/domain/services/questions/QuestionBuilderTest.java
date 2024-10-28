package com.demo.account.src.main.domain.services.questions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.demo.account.domain.enums.GameEnum;
import com.demo.account.domain.services.questions.Question;
import com.demo.account.domain.services.questions.QuestionBuilderImp;

@SpringBootTest
public class QuestionBuilderTest {
    @Test
    @DisplayName("Should create a bigBall Game")
    void test() {
        List<Question> bigBallQuestions = QuestionBuilderImp.umJogo().doBicho().build();
        assertThat(bigBallQuestions.get(0).getType(), is(equalTo(GameEnum.BIG_BALL)));
    }
}
