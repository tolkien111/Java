package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.GoogleResponse;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.GoogleCommunicationException;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.enums.GoogleApiGeocodingStatus;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class GoogleApiResponseValidator {

    public void validateGoogleApiResponse(GoogleResponse body, String locationQueryString) {
        validateStatus(body);
        validateResultsNotEmpty(body, locationQueryString);
    }

    private void validateStatus(GoogleResponse body) {
        if (GoogleApiGeocodingStatus.valueOf(body.getStatus()) != GoogleApiGeocodingStatus.OK)
            throw new GoogleCommunicationException("Error while communicating with Google API, status: " + body.getStatus());
    }

    private void validateResultsNotEmpty(GoogleResponse body, String locationQueryString) {
        if (Objects.isNull(body.getResults()) || body.getResults().isEmpty())
            throw new GoogleCommunicationException("Google API returned no results for the query: " + locationQueryString);
    }
}

