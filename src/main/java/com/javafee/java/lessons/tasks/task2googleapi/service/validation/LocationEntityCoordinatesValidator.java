package com.javafee.java.lessons.tasks.task2googleapi.service.validation;

import com.javafee.java.lessons.tasks.task2googleapi.service.exception.LocationEntityException;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class LocationEntityCoordinatesValidator {

    public void validateLocationCoordinates(String latitude, String longitude) {
        isLatitudeOrLongitudeNotNull(latitude, longitude);
        isLongitudeOrLatitudeNotEmpty(latitude, longitude);
    }

    private void isLatitudeOrLongitudeNotNull(String latitude, String longitude) {
        if (Objects.isNull(latitude) || Objects.isNull(longitude)) {
            throw new LocationEntityException
                    ("Latitude " + (Objects.isNull(latitude) ? "is null" : "is not null") +
                            " and Longitude " + (Objects.isNull(longitude) ? "is null" : "is not null"));
        }
    }

    private void isLongitudeOrLatitudeNotEmpty(String latitude, String longitude) {
        if (latitude.isBlank() || longitude.isBlank()) {
            throw new LocationEntityException
                    ("Latitude " + (latitude.trim().isEmpty() ? "is empty" : "is not empty") +
                            " and Longitude " + (longitude.trim().isEmpty() ? "is empty" : "is not empty"));
        }
    }
}
