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
        if (Objects.equals(latitude, null) || Objects.equals(longitude, null)) {
            throw new LocationEntityException
                    ("Latitude " + (latitude == null ? "is null" : "is not null") +
                            " and Longitude " + (longitude == null ? "is null" : "is not null"));
        }
    }

    private void isLongitudeOrLatitudeNotEmpty(String latitude, String longitude) {
        if (latitude.isBlank() || longitude.isBlank()) {
            throw new LocationEntityException
                    ("Latitude " + (latitude.isBlank() ? "is blank" : "is not blank") +
                    " and Longitude " + (longitude.isBlank() ? "is blank" : "is not blank"));
        }
    }

}
