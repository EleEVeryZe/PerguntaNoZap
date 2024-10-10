package com.demo.account.domain.mongo.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Document("BigBall")
@Data
@AllArgsConstructor
public class BigBall {
    @Id
    private String _id;
    private double value;
    private String date;
    private String teams;
    private String hour;
    @Id
    private String numberId;
}
