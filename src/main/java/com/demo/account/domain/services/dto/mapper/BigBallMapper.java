package com.demo.account.domain.services.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.demo.account.domain.mongo.entities.BigBall;
import com.demo.account.domain.services.dto.BigBallDTO;

@Mapper
public interface BigBallMapper {
    BigBallMapper INSTANCE = Mappers.getMapper(BigBallMapper.class);

    BigBall bigBallDTOToBigBallMongo(BigBallDTO bigBallDTO);
}
