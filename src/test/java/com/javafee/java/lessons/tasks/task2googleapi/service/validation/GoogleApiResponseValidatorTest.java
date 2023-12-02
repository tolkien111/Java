package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.Geometry;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.GoogleResponse;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.Location;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.Result;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.GoogleCommunicationException;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.enums.GoogleApiGeocodingStatus;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GoogleApiResponseValidatorTest {

    @Mock
    private GoogleResponse body;

    static String locationQueryString = "Eiffel Tower Paris";

    private static Stream<GoogleApiGeocodingStatus> provideStatusValuesWithoutOkAndRequestDenied() {
        return Arrays.stream(GoogleApiGeocodingStatus.values())
                .skip(1)
                .limit(3);
    }

    @NotNull
    private static GoogleResponse createFullMockGoogleResponse(@NotNull GoogleApiGeocodingStatus status) {
        final var mockLocation = Mockito.mock(Location.class);
        Mockito.lenient().when(mockLocation.getLat()).thenReturn("0");
        Mockito.lenient().when(mockLocation.getLng()).thenReturn("0");

        final var mockGeometry = Mockito.mock(Geometry.class);
        Mockito.lenient().when(mockGeometry.getLocation()).thenReturn(mockLocation);

        final var mockResult = Mockito.mock(Result.class);
        Mockito.lenient().when(mockResult.getGeometry()).thenReturn(mockGeometry);

        final var results = new ArrayList<Result>();
        results.add(mockResult);

        final var body = Mockito.mock(GoogleResponse.class);
        Mockito.lenient().when(body.getResults()).thenReturn(results);
        Mockito.when(body.getStatus()).thenReturn(status.name());
        return body;
    }

    @Test
    void shouldThrowGoogleCommunicationExceptionWhenBodyIsNull() {
        //GIVEN & WHEN
        body = null;

        //THEN
        assertThrows(GoogleCommunicationException.class,
                () -> GoogleApiResponseValidator.validateGoogleApiResponse(body, locationQueryString));
        assertThatThrownBy(
                () -> GoogleApiResponseValidator.validateGoogleApiResponse(body, locationQueryString))
                .isInstanceOf(GoogleCommunicationException.class)
                .hasMessageContaining("Google API returned no results for the query: " + locationQueryString);
    }

    @Test
    void shouldThrowGoogleCommunicationExceptionWhenResultsAreEmpty() {
        //GIVEN & WHEN
        Mockito.when(body.getResults()).thenReturn(new ArrayList<>());

        //THEN
        assertThrows(GoogleCommunicationException.class,
                () -> GoogleApiResponseValidator.validateGoogleApiResponse(body, locationQueryString));
        assertThatThrownBy(
                () -> GoogleApiResponseValidator.validateGoogleApiResponse(body, locationQueryString))
                .isInstanceOf(GoogleCommunicationException.class)
                .hasMessageContaining("Google API returned no results for the query: " + locationQueryString);
    }
    @Test
    void shouldThrowGoogleCommunicationExceptionWhenStatusIsRequestDenied() {
        //GIVEN & WHEN
        body = createFullMockGoogleResponse(GoogleApiGeocodingStatus.REQUEST_DENIED);

        //THEN
        assertThrows(GoogleCommunicationException.class,
                () -> GoogleApiResponseValidator.validateGoogleApiResponse(body, locationQueryString));
        assertThatThrownBy(
                () -> GoogleApiResponseValidator.validateGoogleApiResponse(body, locationQueryString))
                .isInstanceOf(GoogleCommunicationException.class)
                .hasMessageContaining("Access not approved: incorrect google api key");
    }

    @ParameterizedTest
    @MethodSource(value = "provideStatusValuesWithoutOkAndRequestDenied")
    void shouldThrowGoogleCommunicationExceptionWhenStatusIsNotOkAndRequestDenied(GoogleApiGeocodingStatus status) {
        //GIVEN & WHEN
        body = createFullMockGoogleResponse(status);

        //THEN
        assertThrows(GoogleCommunicationException.class,
                () -> GoogleApiResponseValidator.validateGoogleApiResponse(body, locationQueryString));
        assertThatThrownBy(
                () -> GoogleApiResponseValidator.validateGoogleApiResponse(body, locationQueryString))
                .isInstanceOf(GoogleCommunicationException.class)
                .hasMessageContaining("Error while communicating with Google API, status: " + body.getStatus());
    }
}