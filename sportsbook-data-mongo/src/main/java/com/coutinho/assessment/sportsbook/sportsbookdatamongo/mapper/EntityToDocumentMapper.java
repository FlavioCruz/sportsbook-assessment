package com.coutinho.assessment.sportsbook.sportsbookdatamongo.mapper;

import com.coutinho.assessment.sportsbook.sportsbookmodel.model.MatchEvent;
import org.bson.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityToDocumentMapper {

    EntityToDocumentMapper INSTANCE = Mappers.getMapper(EntityToDocumentMapper.class);

}
