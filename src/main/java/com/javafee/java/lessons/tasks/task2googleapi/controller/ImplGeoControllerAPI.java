package com.javafee.java.lessons.tasks.task2googleapi.controller;

import com.javafee.java.lessons.tasks.task2googleapi.service.LocationService;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationResponseView;
import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ImplGeoControllerAPI implements GeoControllerAPI {

    @NonNull
    private final LocationService locationService;

    @Override
    public ResponseEntity<LocationResponseView> searchForLocation(String locationQueryString, HttpSession session) {
        return ResponseEntity.ok(locationService.searchForLocation(locationQueryString));
    }

    @Override
    public ResponseEntity<LocationResponseView> selectLocation(UUID id, HttpSession session) {
        return null;
    }
}