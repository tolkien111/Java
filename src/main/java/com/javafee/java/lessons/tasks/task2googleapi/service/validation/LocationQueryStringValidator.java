package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.exception.LocationQueryStringException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class LocationQueryStringValidator {

    @Value(value = "${forbidden.characters}")
    private String forbiddenCharacters;

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
//OLD VERSION
//        final String FORBIDDEN_CHARACTERS = "#?&%=+"; // wrzucić do properties i  tutaj @Value
//        for(char c : FORBIDDEN_CHARACTERS.toCharArray()){ // użyć wtedy stream.forEach()
//            if(locationQueryString.indexOf(c) != -1){
//                throw new LocationQueryStringException("locationQueryString contains some forbidden characters: " + FORBIDDEN_CHARACTERS);
//            }
//        }

        forbiddenCharacters.chars().mapToObj(c -> (char) c).forEach(c -> {
            if (locationQueryString.indexOf(c) != -1)
                throw new LocationQueryStringException("locationQueryString contains some forbidden characters: " + forbiddenCharacters);
        });
    }
}

