package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.exception.LocationEntityException;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class LocationEntityCoordinatesValidator {

    public void validateLocationCoordinatesEntity(String latitude, String longitude) {
        isLatitudeNotNullOrEmpty(latitude);
        isLongitudeNotNullOrEmpty(longitude);
    }

    private void isLatitudeNotNullOrEmpty(String latitude) {
        if (Objects.isNull(latitude) || latitude.trim().isEmpty()) {
            throw new LocationEntityException("latitude is null or empty" );
        }
    }

    private void isLongitudeNotNullOrEmpty(String longitude) {
        if (Objects.isNull(longitude) || longitude.trim().isEmpty()) {
            throw new LocationEntityException("longitude is null or empty" );
        }
    }

}
