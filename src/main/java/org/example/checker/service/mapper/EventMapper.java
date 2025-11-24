package org.example.checker.service.mapper;

import org.example.checker.dto.request.EventCreateRequest;
import org.example.checker.dto.response.EventResponse;
import org.example.checker.dto.response.EventResponseFull;
import org.example.checker.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {EventSessionMapper.class})
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toEntity(EventCreateRequest dto);
    EventResponse toDto(Event event);

    EventResponseFull toFullDto(Event event);
}
