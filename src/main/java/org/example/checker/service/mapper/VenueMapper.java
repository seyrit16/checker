package org.example.checker.service.mapper;

import org.example.checker.dto.request.VenueCreateRequest;
import org.example.checker.model.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VenueMapper {
    VenueMapper INSTANCE = Mappers.getMapper(VenueMapper.class);

    Venue toEntity(VenueCreateRequest dto);
}
