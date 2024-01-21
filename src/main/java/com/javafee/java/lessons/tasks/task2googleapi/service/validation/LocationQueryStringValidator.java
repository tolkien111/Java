package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.exception.LocationQueryStringException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class LocationQueryStringValidator {

    @Value(value = "#{'${forbidden.characters}'.split(',')}")
    private List<String> forbiddenCharacters;

    @Value(value = "${max.address.length}")
    private int MAX_LENGTH;

    public void validateLocalQueryString(String localQueryString) {
        isNullOrEmpty(localQueryString);
        isTooLong(localQueryString);
        haveForbiddenCharacters(localQueryString);
    }

    private void isNullOrEmpty(String locationQueryString) {
        if (Objects.isNull(locationQueryString) || locationQueryString.trim().isEmpty())
            throw new LocationQueryStringException("locationQueryString is null or empty");
    }

    private void isTooLong(String locationQueryString) {
        if (locationQueryString.length() >= MAX_LENGTH)
            throw new LocationQueryStringException("locationQueryString is too long");
    }

    private void haveForbiddenCharacters(String locationQueryString) {
        forbiddenCharacters.forEach(c -> {
            if (locationQueryString.contains(c))
                throw new LocationQueryStringException("locationQueryString contains forbidden character: " + c);
        });
    }
}

