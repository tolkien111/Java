package com.javafee.java.lessons.tasks.task2googleapi.controller;

import com.javafee.java.lessons.tasks.task2googleapi.service.LocationService;
import com.javafee.java.lessons.tasks.task2googleapi.service.LocationServiceV2;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.v2.LocationView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImplGeoControllerAPI implements GeoControllerAPI{

    @NonNull
    LocationServiceV2 locationService;

    @Override
    public ResponseEntity<LocationView> searchForLocation(String locationQueryString) {
        return ResponseEntity.ok(locationService.searchForLocation(locationQueryString));
    }
}
