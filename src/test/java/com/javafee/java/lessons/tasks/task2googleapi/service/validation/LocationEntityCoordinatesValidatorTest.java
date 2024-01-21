package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.Geometry;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.GoogleResponse;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.Location;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.Result;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.LocationEntityException;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.enums.GoogleApiGeocodingStatus;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class LocationEntityCoordinatesValidatorTest {

    @Mock
    protected GoogleResponse body;

    @NotNull
    private static GoogleResponse createFullMockGoogleResponse(String latitude, String longitude) {
        final var mockLocation = Mockito.mock(Location.class);
        Mockito.lenient().when(mockLocation.getLat()).thenReturn(latitude);
        Mockito.lenient().when(mockLocation.getLng()).thenReturn(longitude);

        final var mockGeometry = Mockito.mock(Geometry.class);
        Mockito.lenient().when(mockGeometry.getLocation()).thenReturn(mockLocation);

        final var mockResult = Mockito.mock(Result.class);
        Mockito.lenient().when(mockResult.getGeometry()).thenReturn(mockGeometry);

        final var results = new ArrayList<Result>();
        results.add(mockResult);

        var body = Mockito.mock(GoogleResponse.class);
        Mockito.lenient().when(body.getResults()).thenReturn(results);
        Mockito.lenient().when(body.getStatus()).thenReturn(GoogleApiGeocodingStatus.OK.name());
        return body;
    }

    @ParameterizedTest
    @CsvSource({
            ", 0",
            "0,",
            ","
    })
    void shouldThrowExceptionWhenLatitudeOrLongitudeIsNull(String latitude, String longitude) {
        //GIVEN & WHEN
        body = createFullMockGoogleResponse(latitude, longitude);
        //THEN
        Assertions.assertThatThrownBy(
                        () -> LocationEntityCoordinatesValidator.validateLocationCoordinates(latitude, longitude))
                .isInstanceOf(LocationEntityException.class)
                .hasMessageContaining(
                        "Latitude " + (latitude == null ? "is null" : "is not null") +
                                " and Longitude " + (longitude == null ? "is null" : "is not null"));

    }

    @ParameterizedTest
    @CsvSource({
            "' ', 0",
            "0,' '",
            "' ',' '"
    })
    void shouldThrowExceptionWhenLatitudeIsNull(String latitude, String longitude) {
        //GIVEN & WHEN
        body = createFullMockGoogleResponse(latitude, longitude);
        //THEN
        Assertions.assertThatThrownBy(
                        () -> LocationEntityCoordinatesValidator.validateLocationCoordinates(latitude, longitude))
                .isInstanceOf(LocationEntityException.class)
                .hasMessageContaining(
                        "Latitude " + (latitude.trim().isEmpty() ? "is empty" : "is not empty") +
                                " and Longitude " + (longitude.trim().isEmpty() ? "is empty" : "is not empty"));

    }
}