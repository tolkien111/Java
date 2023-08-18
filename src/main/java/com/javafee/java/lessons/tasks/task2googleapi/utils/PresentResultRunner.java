package com.javafee.java.lessons.tasks.task2googleapi.utils;

import com.javafee.java.lessons.tasks.task2googleapi.service.LocationService;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.LocationView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PresentResultRunner implements CommandLineRunner {

    @NonNull
    private LocationService locationService;

    @Value("${google.api.location-sample-query}")
    private String locationQueryString;

    @Override
    public void run(String... args) {
        LocationView locationView = locationService.searchForLocation(locationQueryString);
        System.out.println(locationView);
    }
}
