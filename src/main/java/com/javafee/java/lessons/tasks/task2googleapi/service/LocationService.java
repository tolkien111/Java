package com.javafee.java.lessons.tasks.task2googleapi.service;


import com.javafee.java.lessons.tasks.task2googleapi.entity.LocationEntity;
import com.javafee.java.lessons.tasks.task2googleapi.repository.LocationRepository;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.LocationView;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.GoogleResponse;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {

    @NonNull
    private RestTemplate restTemplate;

    @NonNull
    LocationRepository repository;

    @Value("${google.api.url}")
    private String googleApiUrl;

    @Value("${google.api.keys}")
    private String googleApiKey;


    public LocationView searchForLocation(String locationQueryString) {
        String url = googleApiUrl + UriUtils.decode(locationQueryString, StandardCharsets.UTF_8) + "&key=" + googleApiKey;
        ResponseEntity<GoogleResponse> response = restTemplate.getForEntity(url, GoogleResponse.class);

        LocationEntity location = Optional.ofNullable(response.getBody())
                .map(body -> body.results())
                .filter(results -> !results.isEmpty())
                .map(results -> results.get(0))
                .map(result -> result.geometry())
                .map(geometry -> geometry.location())
                .map(locationResult -> LocationEntity
                        .builder()
                        .addressDescription(locationQueryString)
                        .latitude(Double.parseDouble(locationResult.lat()))
                        .longitude(Double.parseDouble(locationResult.lng()))
                        .build())
                .orElseThrow(() -> new RuntimeException("JSON response processing problem"));

        repository.save(location);
        return new LocationView(locationQueryString, location.getLatitude(), location.getLongitude());

    }


}
