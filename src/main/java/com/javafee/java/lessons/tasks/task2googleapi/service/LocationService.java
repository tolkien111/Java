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
        GoogleResponse body = getGoogleResponseBody(locationQueryString);

        if (body == null) {
            throw new RuntimeException("Received null response from Google API");
        }

        String lat = body.results().get(0).geometry().location().lat();
        String lng = body.results().get(0).geometry().location().lng();

        if (repository.locationExists(lat, lng)) {
            return transformEntityToView(lat, lng);
        }

        return createAndSaveLocation(locationQueryString, lat, lng);
    }

    private String buildGoogleApiUrl(String locationQueryString) {
        return googleApiUrl + UriUtils.encode(locationQueryString, StandardCharsets.UTF_8) + "&key=" + googleApiKey;
    }

    private GoogleResponse getGoogleResponseBody(String locationQueryString) {
        String url = buildGoogleApiUrl(locationQueryString);
        ResponseEntity<GoogleResponse> response = restTemplate.getForEntity(url, GoogleResponse.class);
        return response.getBody();
    }

    private LocationView transformEntityToView(String lat, String lng) {
        return repository.readLocation(lat, lng).toView();
    }

    private LocationView createAndSaveLocation(String locationQueryString, String lat, String lng) {
        LocationEntity location = LocationEntity.builder()
                .addressDescription(locationQueryString)
                .latitude(lat)
                .longitude(lng)
                .build();

        repository.save(location);
        return new LocationView(locationQueryString, location.getLatitude(), location.getLongitude());
    }
}
