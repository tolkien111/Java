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
        var mockLocation = Mockito.mock(Location.class);
        Mockito.lenient().when(mockLocation.getLat()).thenReturn(latitude);
        Mockito.lenient().when(mockLocation.getLng()).thenReturn(longitude);

        var mockGeometry = Mockito.mock(Geometry.class);
        Mockito.lenient().when(mockGeometry.getLocation()).thenReturn(mockLocation);

        var mockResult = Mockito.mock(Result.class);
        Mockito.lenient().when(mockResult.getGeometry()).thenReturn(mockGeometry);

        var results = new ArrayList<Result>();
        results.add(mockResult);

        var body = Mockito.mock(GoogleResponse.class);
        Mockito.lenient().when(body.getResults()).thenReturn(results);
        Mockito.lenient().when(body.getStatus()).thenReturn(GoogleApiGeocodingStatus.OK.name());
        return body;
    }


    @ParameterizedTest
    @CsvSource({
            "NULL, 0",
            "0, NULL",
            "NULL, NULL"
    })
    void shouldThrowExceptionWhenLatitudeIsNull(String latitude, String longitude) {
        //GIVEN & WHEN
        var lat = "NULL".equals(latitude) ? null : latitude;
        var lng = "NULL".equals(longitude) ? null : latitude;

        body = createFullMockGoogleResponse(lat, lng);

        Assertions.assertThatThrownBy(
                        () -> LocationEntityCoordinatesValidator.validateLocationCoordinates(lat, lng))
                .isInstanceOf(LocationEntityException.class)
                .hasMessageContaining(
                        "Latitude " + (lat == null ? "is null" : "is not null") +
                                " and Longitude " + (lng == null ? "is null" : "is not null"));

    }

    //TODO For consultation
//    @Test
//    void shouldThrowExceptionWhenLatitudeIsNull() {
//        //GIVEN & WHEN
//        body = createFullMockGoogleResponse(null, "0");
//        var latitude = body.getResults().get(0).getGeometry().getLocation().getLat();
//        var longitude = body.getResults().get(0).getGeometry().getLocation().getLng();
//
//        //THEN
//        Assertions.assertThatThrownBy(
//                () -> LocationEntityCoordinatesValidator.validateLocationCoordinates(latitude, longitude))
//                .isInstanceOf(LocationEntityException.class)
//                .hasMessageContaining("Latitude is null and Longitude is not null");
//    }
//
//    @Test
//    void shouldThrowExceptionWhenLongitudeIsNull() {
//        //GIVEN & WHEN
//        body = createFullMockGoogleResponse("0", null);
//        var latitude = body.getResults().get(0).getGeometry().getLocation().getLat();
//        var longitude = body.getResults().get(0).getGeometry().getLocation().getLng();
//
//        //THEN
//        Assertions.assertThatThrownBy(
//                        () -> LocationEntityCoordinatesValidator.validateLocationCoordinates(latitude, longitude))
//                .isInstanceOf(LocationEntityException.class)
//                .hasMessageContaining("Latitude is not null and Longitude is null");
//    }
//
//    @Test
//    void shouldThrowExceptionWhenLatitudeAndLongitudeIsNull() {
//        //GIVEN & WHEN
//        body = createFullMockGoogleResponse(null, null);
//        var latitude = body.getResults().get(0).getGeometry().getLocation().getLat();
//        var longitude = body.getResults().get(0).getGeometry().getLocation().getLng();
//
//        //THEN
//        Assertions.assertThatThrownBy(
//                        () -> LocationEntityCoordinatesValidator.validateLocationCoordinates(latitude, longitude))
//                .isInstanceOf(LocationEntityException.class)
//                .hasMessageContaining("Latitude is null and Longitude is null");
//    }
}