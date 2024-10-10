package com.demo.account.domain.mongo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.demo.account.domain.mongo.entities.AccountMongo;

public interface AccountServiceMongo extends MongoRepository<AccountMongo, String> {
    List<AccountMongo> findByEmail(String email);
}
