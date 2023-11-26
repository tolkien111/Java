package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.Geometry;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.GoogleResponse;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.Location;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.Result;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.GoogleCommunicationException;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.enums.GoogleApiGeocodingStatus;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GoogleApiResponseValidatorTest {

    @Mock
    GoogleResponse body;

    private static Stream<GoogleApiGeocodingStatus> provideStatusValuesWithoutOk() {
        return Arrays.stream(GoogleApiGeocodingStatus.values())
                .skip(1)
                .limit(4);
    }

    private static Stream<GoogleApiGeocodingStatus> provideStatusValuesOnlyOk() {
        return Arrays.stream(GoogleApiGeocodingStatus.values())
                .skip(0)
                .limit(1);
    }

    private static GoogleResponse createFullMockGoogleResponse(GoogleApiGeocodingStatus status) {
        var mockLocation = Mockito.mock(Location.class);
        Mockito.lenient().when(mockLocation.getLat()).thenReturn("0");
        Mockito.lenient().when(mockLocation.getLng()).thenReturn("0");

        var mockGeometry = Mockito.mock(Geometry.class);
        Mockito.lenient().when(mockGeometry.getLocation()).thenReturn(mockLocation);

        var mockResult = Mockito.mock(Result.class);
        Mockito.lenient().when(mockResult.getGeometry()).thenReturn(mockGeometry);

        var results = new ArrayList<Result>();
        results.add(mockResult);

        var body = Mockito.mock(GoogleResponse.class);
        Mockito.lenient().when(body.getResults()).thenReturn(results);
        Mockito.when(body.getStatus()).thenReturn(status.name());
        return body;
    }

    @ParameterizedTest
    @MethodSource(value = "provideStatusValuesWithoutOk")
    void shouldThrowGoogleCommunicationExceptionWhenStatusIsNotOk(GoogleApiGeocodingStatus status) {
        //GIVEN
        String address = "Dworzec Główny Gdańsk";
        body = createFullMockGoogleResponse(status);

        //WHEN & THEN
        System.out.println(body.getStatus());
        assertThrows(GoogleCommunicationException.class,
                () -> GoogleApiResponseValidator.validateGoogleApiResponse(body, address));
    }

    void shouldThrowGoogleCommunicationExceptionWhenResultsAreNull() {
    }
}