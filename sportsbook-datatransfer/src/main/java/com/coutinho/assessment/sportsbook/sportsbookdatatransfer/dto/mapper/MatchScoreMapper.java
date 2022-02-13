package com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.mapper;

import com.coutinho.assessment.sportsbook.sportsbookdatatransfer.dto.MatchEventDTO;
import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchScoreMapper {

    MatchScoreMapper INSTANCE = Mappers.getMapper(MatchScoreMapper.class);

    default MatchEvent dtoToEntity(MatchEventDTO dto){
        MatchEvent event = new MatchEvent();
        String[] eventNameArray = dto.getEvent().split("vs");
        String[] eventScoreArray = dto.getScore().split("-");
        event.setTeamA(eventNameArray[0].trim());
        event.setScoreTeamA(Integer.parseInt(eventScoreArray[0].trim()));
        event.setTeamB(eventNameArray[1].trim());
        event.setScoreTeamB(Integer.parseInt(eventScoreArray[1].trim()));

        return event;
    }

    default MatchEventDTO entityToDto(MatchEvent event){
        MatchEventDTO dto = new MatchEventDTO();
        dto.setEvent(event.getTeamA() + " vs " + event.getTeamB());
        dto.setScore(event.getScoreTeamA() + "-" + event.getScoreTeamB());

        return dto;
    }


}
