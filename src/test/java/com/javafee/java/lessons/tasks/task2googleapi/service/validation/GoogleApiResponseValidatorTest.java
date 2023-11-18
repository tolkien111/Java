package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.GoogleResponse;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.GoogleCommunicationException;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.enums.GoogleApiGeocodingStatus;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GoogleApiResponseValidatorTest {

    @Mock
    private GoogleResponse body;

    private static Stream<GoogleApiGeocodingStatus> provideStatusValues() {
        return Arrays.stream(GoogleApiGeocodingStatus.values())
                .skip(0)
                .limit(4);
    }

    @ParameterizedTest
    @MethodSource(value = "provideStatusValues")
    void shouldThrowGoogleCommunicationExceptionWhenStatusIsNotOk(GoogleApiGeocodingStatus status) {
        String address = "Dworzec Główny Gdańsk";

        Mockito.when(body.getStatus()).thenReturn(String.valueOf(status));
        System.out.println(body.getStatus());
        assertThrows(GoogleCommunicationException.class,
                () -> GoogleApiResponseValidator.validateGoogleApiResponse(body, address));

    }
}