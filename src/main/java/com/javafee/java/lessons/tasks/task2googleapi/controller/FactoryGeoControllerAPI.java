package com.javafee.java.lessons.tasks.task2googleapi.controller;

import com.javafee.java.lessons.tasks.task2googleapi.service.LocationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FactoryGeoControllerAPI {

    public static GeoControllerAPI createLocation (@NonNull LocationService locationService){
        return locationQueryString -> ResponseEntity.ok(locationService.searchForLocation(locationQueryString));
    }
}
