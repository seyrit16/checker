package org.example.checker.service.mapper;

import org.example.checker.dto.request.EventCreateRequest;
import org.example.checker.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toEntity(EventCreateRequest dto);
}
