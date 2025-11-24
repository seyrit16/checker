package org.example.checker.service.mapper;

import org.example.checker.dto.request.EventSessionCreateRequest;
import org.example.checker.model.EventSession;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = VenueMapper.class)
public interface EventSessionMapper {
    EventSessionMapper INSTANCE = Mappers.getMapper(EventSessionMapper.class);

    EventSession toEntity(EventSessionCreateRequest dto);
}
