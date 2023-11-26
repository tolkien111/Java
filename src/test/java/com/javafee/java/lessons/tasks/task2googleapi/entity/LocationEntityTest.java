package com.javafee.java.lessons.tasks.task2googleapi.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class LocationEntityTest extends EntityTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/locationEntityData.csv", numLinesToSkip = 1)
    void shouldSaveAddressToDb(String address, String latitude, String longitude) {
        //GIVEN
        final var location = LocationEntity.builder()
                .addressDescription(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        //WHEN
        persist(location);

        //THEN
        final var readLocation = entityManager.find(LocationEntity.class, location.getId());
        assertEquals(location, readLocation);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/locationEntityData.csv", numLinesToSkip = 1)
    void shouldThrowExceptionWithoutArgumentAddressDescription(String latitude, String longitude) {
        //GIVEN & WHEN & THEN
        assertThrows(NullPointerException.class, LocationEntity.builder()
                .latitude(latitude)
                .longitude(longitude)::build);
    }
}