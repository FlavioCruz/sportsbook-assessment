package com.coutinho.assessment.sportsbook.mapper;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import org.bson.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityToDocumentMapper {

    EntityToDocumentMapper INSTANCE = Mappers.getMapper(EntityToDocumentMapper.class);

    default Document entityToDocument(MatchEvent matchEvent){
        return new Document()
                .append("Team A", matchEvent.getTeamA())
                .append("Score team A", matchEvent.getScoreTeamA())
                .append("Team B", matchEvent.getTeamB())
                .append("Score team B", matchEvent.getScoreTeamB());
    }
}
