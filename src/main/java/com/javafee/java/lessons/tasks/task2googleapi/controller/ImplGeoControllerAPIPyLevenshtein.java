package com.javafee.java.lessons.tasks.task2googleapi.controller;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationResponseView;
import com.javafee.java.lessons.tasks.task2googleapi.service.pylevenshtein.LocationServicePyLevenshtein;
import com.javafee.java.lessons.tasks.task2googleapi.service.pylevenshtein.QueryPyLevenshteinService;
import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ImplGeoControllerAPIPyLevenshtein implements GeoControllerAPI {

    @NonNull
    private final LocationServicePyLevenshtein locationServicePyLevenshtein;
    @NonNull
    private QueryPyLevenshteinService query;

    @Override
    @GetMapping("v1/location/{locationQueryString}")
    public ResponseEntity<LocationResponseView> searchForLocation(@PathVariable String locationQueryString, HttpSession session) {
        session.setAttribute("locationQueryString", locationQueryString);
        return ResponseEntity.ok(locationServicePyLevenshtein.searchForLocations(locationQueryString));
    }

    @Override
    @GetMapping("v1/location/select/{id}")
    public ResponseEntity<LocationResponseView> selectLocation(@PathVariable UUID id, HttpSession session) {
        return ResponseEntity.ok(query.getLocationResponseView(id, session));
    }
}