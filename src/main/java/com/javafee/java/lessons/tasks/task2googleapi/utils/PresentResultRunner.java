package com.javafee.java.lessons.tasks.task2googleapi.utils;

import com.javafee.java.lessons.tasks.task2googleapi.service.LocationService;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PresentResultRunner implements CommandLineRunner {

    @NonNull
    private LocationService locationService;

    @Value(value = "${google.api.location-sample-query}")
    private String locationQueryString;

    @Override
    public void run(String... args) {
        LocationView locationView = locationService.searchForLocation(locationQueryString);
        log.info(String.valueOf(locationView));
    }
}
