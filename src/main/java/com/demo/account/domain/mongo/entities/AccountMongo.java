package com.demo.account.domain.mongo.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Document("accounts")
@Data
@AllArgsConstructor
public class AccountMongo {
    @Id
    private String _id;
    private String email;
    private String name;
    private String password;
    private Boolean active;
}
