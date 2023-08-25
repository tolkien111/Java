package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.GoogleResponse;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.GoogleCommunicationException;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.enums.GoogleApiGeocodingStatus;

import java.util.Objects;

public class GoogleApiResponseValidator {

    public static void validateGoogleApiResponse(GoogleResponse body, String locationQueryString) {
        validateStatus(body);
        validateResultsNotEmpty(body, locationQueryString);
    }

    private static void validateStatus(GoogleResponse body) {
        if (GoogleApiGeocodingStatus.valueOf(body.getStatus()) != GoogleApiGeocodingStatus.OK) {
            throw new GoogleCommunicationException("Error while communicating with Google API, status: " + body.getStatus());
        }
    }

    private static void validateResultsNotEmpty(GoogleResponse body, String locationQueryString) {
        if (Objects.isNull(body.getResults()) || body.getResults().isEmpty()) {
            throw new GoogleCommunicationException("Google API returned no results for the query: " + locationQueryString);
        }
    }
}

