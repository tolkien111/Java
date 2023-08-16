package com.javafee.java.lessons.tasks.task2googleapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javafee.java.lessons.tasks.task2googleapi.entity.Location;
import com.javafee.java.lessons.tasks.task2googleapi.repository.LocationRepository;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.LocationView;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
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
        String url = googleApiUrl + UriUtils.decode(locationQueryString, StandardCharsets.UTF_8) + "&key=" + googleApiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode parse = mapper.readTree(response.getBody());

            //Get value lat and lng
            double lat = parse.path("results").get(0).path("geometry").path("location").path("lat").asDouble();
            double lng = parse.path("results").get(0).path("geometry").path("location").path("lng").asDouble();

            Location location = new Location(locationQueryString, lat, lng);
            repository.save(location);

            return new LocationView(locationQueryString, lat, lng);

        } catch (IOException e) {
            throw new RuntimeException("JSON response processing problem", e);
        }


    }


}
