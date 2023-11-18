package com.javafee.java.lessons.tasks.task2googleapi.repository;

import com.javafee.java.lessons.tasks.task2googleapi.entity.LocationEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LocationRepositoryTest {

    @Autowired
    private LocationRepository repository;

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/locationEntityData.csv", numLinesToSkip = 1)
    void shouldReadLocationFromDb(String address, String latitude, String longitude) {
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
        assertEquals(location.getAddressDescription(), readLocation.getAddressDescription());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/locationEntityData.csv", numLinesToSkip = 1)
    void shouldExistsLocationInDb(String address, String latitude, String longitude) {
        //GIVEN
        final var location = LocationEntity.builder()
                .addressDescription(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        //WHEN
        repository.saveAndFlush(location);

        //THEN
        assertTrue(repository.locationExists(latitude, longitude));
    }
}