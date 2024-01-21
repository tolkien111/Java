package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.GoogleResponse;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.GoogleCommunicationException;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.enums.GoogleApiGeocodingStatus;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@UtilityClass
public class GoogleApiResponseValidator {

    public void validateGoogleApiResponse(GoogleResponse body, String locationQueryString) {
        validateNotEmptyOrNotNullResults(body, locationQueryString);
        validateAccess(body);
        validateStatus(body);
    }

    private void validateNotEmptyOrNotNullResults(GoogleResponse body, String locationQueryString) {
        if (Objects.isNull(body) || body.getResults().isEmpty())
            throw new GoogleCommunicationException("Google API returned no results for the query: " + locationQueryString);
      
    private void validateAccess(GoogleResponse body){
        if (GoogleApiGeocodingStatus.valueOf(body.getStatus()) == GoogleApiGeocodingStatus.REQUEST_DENIED)
            throw new GoogleCommunicationException("Access not approved: incorrect google api key");
      
    private void validateStatus(GoogleResponse body) {
        if (GoogleApiGeocodingStatus.valueOf(body.getStatus()) != GoogleApiGeocodingStatus.OK)
            throw new GoogleCommunicationException("Error while communicating with Google API, status: " + body.getStatus());