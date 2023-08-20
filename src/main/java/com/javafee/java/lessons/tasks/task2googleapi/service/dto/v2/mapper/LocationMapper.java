package com.javafee.java.lessons.tasks.task2googleapi.service.dto.v2.mapper;

import com.javafee.java.lessons.tasks.task2googleapi.entity.LocationEntity;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.v2.LocationView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    static LocationMapper getInstance() {
        return Mappers.getMapper(LocationMapper.class);
    }

    @Mapping(target = "addressDescription", source = "addressDescription")
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    LocationView entityToView(LocationEntity locationEntity);
}
