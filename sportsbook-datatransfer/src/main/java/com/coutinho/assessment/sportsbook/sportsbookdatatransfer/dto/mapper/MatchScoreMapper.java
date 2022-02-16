package com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.mapper;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchScoreMapper {

    MatchScoreMapper INSTANCE = Mappers.getMapper(MatchScoreMapper.class);

    @Mapping(target = "id", source = "event")
    MatchEvent dtoToEntity(MatchEventDTO dto);

    @Mapping(target = "event", source = "id")
    MatchEventDTO entityToDto(MatchEvent event);
}
