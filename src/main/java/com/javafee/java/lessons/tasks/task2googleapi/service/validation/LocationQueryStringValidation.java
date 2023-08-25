package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.exception.LocationQueryStringException;

import java.util.Objects;

public class LocationQueryStringValidation {


    public static void validateLocalQueryString(String localQueryString){
        isNullOrEmpty(localQueryString);
        isTooLong(localQueryString);
        haveForbiddenCharacters(localQueryString);
    }

    private static void isNullOrEmpty(String locationQueryString) {
        if (Objects.isNull(locationQueryString) || locationQueryString.trim().isEmpty())
            throw new LocationQueryStringException("locationQueryString is null or empty");

    }

    private static void isTooLong(String locationQueryString) {
        final int MAX_LENGTH = 280; // from Google Documentation
        if (locationQueryString.length() >= MAX_LENGTH)
            throw new LocationQueryStringException("locationQueryString is too long");

    }

    private static void haveForbiddenCharacters(String locationQueryString){
        final String FORBIDDEN_CHARACTERS = "#?&%=+";
        for(char c : FORBIDDEN_CHARACTERS.toCharArray()){
            if(locationQueryString.indexOf(c) != -1){
                throw new LocationQueryStringException("locationQueryString contains forbidden characters: " + FORBIDDEN_CHARACTERS);
            }
        }
    }
}
