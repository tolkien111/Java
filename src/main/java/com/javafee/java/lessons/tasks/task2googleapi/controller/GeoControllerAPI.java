package com.javafee.java.lessons.tasks.task2googleapi.controller;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationResponseView;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/geo", produces = MediaType.APPLICATION_JSON_VALUE)
public interface GeoControllerAPI {
    @GetMapping("/location/{locationQueryString}")
    ResponseEntity<LocationResponseView> searchForLocation(@PathVariable String locationQueryString);
}
