package com.javafee.java.lessons.tasks.task2googleapi.utils;

import com.javafee.java.lessons.tasks.task2googleapi.service.LocationServiceV2;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.v2.LocationView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PresentResultRunner implements CommandLineRunner {

    @NonNull
    private LocationServiceV2 locationService;

    @Value("${google.api.location-sample-query}")
    private String locationQueryString;

    @Override
    public void run(String... args) {
        LocationView locationView = locationService.searchForLocation(locationQueryString);
        System.out.println(locationView);
    }
}
