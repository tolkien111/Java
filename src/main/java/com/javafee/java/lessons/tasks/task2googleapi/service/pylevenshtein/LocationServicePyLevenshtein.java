package com.javafee.java.lessons.tasks.task2googleapi.service.pylevenshtein;

import com.javafee.java.lessons.tasks.task2googleapi.service.LocationService;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationIdView;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationResponseView;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.LocationQueryStringValidator;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationServicePyLevenshtein {

    @NonNull
    private final LocationQueryStringValidator validator;
    @NonNull
    private final LocationService locationService;
    @NotNull
    private final PyLevenshteinService pyLevenshteinService;

    public LocationResponseView searchForLocations(String locationQueryString) {
        validator.validateLocalQueryString(locationQueryString);
        List<LocationIdView> similarLocations = pyLevenshteinService.searchSimilarLocations(locationQueryString);
        if (!similarLocations.isEmpty() && similarLocations.size() > 1) {
            return new LocationResponseView(similarLocations);
        } else {
            return new LocationResponseView(locationService.searchForLocation(locationQueryString).getLocationView());
        }
    }
}