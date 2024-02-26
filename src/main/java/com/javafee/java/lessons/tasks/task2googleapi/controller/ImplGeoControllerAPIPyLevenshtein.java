package com.javafee.java.lessons.tasks.task2googleapi.controller;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationResponseView;
import com.javafee.java.lessons.tasks.task2googleapi.service.pylevenshtein.LocationServicePyLevenshtein;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImplGeoControllerAPIPyLevenshtein implements GeoControllerAPI {

    @NonNull
    LocationServicePyLevenshtein locationServicePyLevenshtein;

    @Override
    @GetMapping("v1/location/{locationQueryString}")
    public ResponseEntity<LocationResponseView> searchForLocation(@PathVariable String locationQueryString) {
        return ResponseEntity.ok(locationServicePyLevenshtein.searchForLocations(locationQueryString));
    }
}
