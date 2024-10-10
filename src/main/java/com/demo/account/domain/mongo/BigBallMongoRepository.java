package com.demo.account.domain.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.demo.account.domain.mongo.entities.BigBall;

public interface BigBallMongoRepository extends MongoRepository<BigBall, String> {

}
