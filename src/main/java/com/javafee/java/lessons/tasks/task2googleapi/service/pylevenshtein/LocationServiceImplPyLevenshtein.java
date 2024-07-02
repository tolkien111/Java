package com.javafee.java.lessons.tasks.task2googleapi.service.pylevenshtein;

import com.javafee.java.lessons.tasks.task2googleapi.service.LocationService;
import com.javafee.java.lessons.tasks.task2googleapi.service.LocationServiceImpl;
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
public class LocationServiceImplPyLevenshtein implements LocationService {

    @NonNull
    private final LocationQueryStringValidator validator;
    @NonNull
    private final LocationServiceImpl locationServiceImpl;
    @NotNull
    private final PyLevenshteinSimilaritySearcherService pyLevenshteinSimilaritySearcherService;

    @Override
    public LocationResponseView searchForLocation(String locationQueryString) {
        validator.validateLocalQueryString(locationQueryString);
        List<LocationIdView> similarLocations = pyLevenshteinSimilaritySearcherService.searchSimilarLocations(locationQueryString);
        return getLocationResponseView(locationQueryString, similarLocations);
    }

    @NotNull
    private LocationResponseView getLocationResponseView(String locationQueryString, List<LocationIdView> similarLocations) {
        if (!similarLocations.isEmpty() && similarLocations.size() > 1) {
            return new LocationResponseView(similarLocations);
        } else {
            return new LocationResponseView(locationServiceImpl.searchForLocation(locationQueryString).getLocationView());
            // TODO decouple from LocationServiceImpl and remove the injection of LocationServiceImpl
        }
    }
    // TODO Prepare this File and concat with QueryPyLevenshteinService
}