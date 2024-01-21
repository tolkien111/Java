package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.exception.LocationQueryStringException;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional
class LocationQueryStringValidatorTest {

    @Autowired
    LocationQueryStringValidator validator;

    private String locationQueryString;

    @Test
    void shouldThrowExceptionWhenLocationQueryStringIsNull() {
        //GIVEN & WHEN
        locationQueryString = null;

        //THEN
        Assertions.assertThatThrownBy(() -> validator.validateLocalQueryString(locationQueryString))
                .isInstanceOf(LocationQueryStringException.class)
                .hasMessageContaining("locationQueryString is null or empty");
    }

    @Test
    void shouldThrowExceptionWhenLocationQueryStringIsEmpty() {
        //GIVEN & WHEN
        locationQueryString = "   ";

        //THEN
        Assertions.assertThatThrownBy(() -> validator.validateLocalQueryString(locationQueryString))
                .isInstanceOf(LocationQueryStringException.class)
                .hasMessageContaining("locationQueryString is null or empty");
    }

    @Test
    void shouldThrowExceptionWhenLocationQueryStringIsTooLong() {
        //GIVEN & WHEN
        locationQueryString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" +
                "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                "voluptate velit esse cillum.";

        //THEN
        Assertions.assertThatThrownBy(() -> validator.validateLocalQueryString(locationQueryString))
                .isInstanceOf(LocationQueryStringException.class)
                .hasMessageContaining("locationQueryString is too long");
    }

    @Test
    void shouldThrowExceptionWhenLocationQueryStringContainsForbiddenCharacter() {
        //GIVEN & WHEN
        locationQueryString = "Eiffel # Tower Paris";

        //THEN
        Assertions.assertThatThrownBy(() -> validator.validateLocalQueryString(locationQueryString))
                .isInstanceOf(LocationQueryStringException.class)
                .hasMessageContaining("locationQueryString contains forbidden character: ");
    }
}