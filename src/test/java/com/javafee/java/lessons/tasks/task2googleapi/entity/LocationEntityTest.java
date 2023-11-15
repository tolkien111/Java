package com.javafee.java.lessons.tasks.task2googleapi.entity;

import com.javafee.java.lessons.tasks.task2googleapi.repository.LocationRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class LocationEntityTest extends EntityTest{

    @Autowired
    private LocationRepository repository;

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/locationEntityData.csv", numLinesToSkip = 1)
    void shouldSaveAddressToDb(String address, String latitude, String longitude){
        //GIVEN
        final var location = LocationEntity.builder()
                .addressDescription(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        //WHEN
        repository.saveAndFlush(location);

        //THEN
        final var readLocation = repository.readLocation(latitude, longitude);
        assertEquals(location, readLocation);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/locationEntityData.csv", numLinesToSkip = 1)
    void shouldSaveAddressToDb_V2(String address, String latitude, String longitude){
        //GIVEN
        final var location = LocationEntity.builder()
                .addressDescription(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        //WHEN
        persist(location);

        //THEN
        final var readLocation = repository.readLocation(latitude, longitude);
        assertEquals(location, readLocation);
    }

}