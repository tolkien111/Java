package com.javafee.java.lessons.tasks.task2googleapi.service;


import com.javafee.java.lessons.tasks.task2googleapi.entity.LocationEntity;
import com.javafee.java.lessons.tasks.task2googleapi.repository.LocationRepository;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.v2.LocationView;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.v2.googlelocationpath.GoogleResponse;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.v2.mapper.LocationMapper;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationServiceV2 {

    @NonNull
    private final LocationRepository repository;

    @NonNull
    private final RestTemplate restTemplate;

    @Value(value = "${google.api.url}")
    private String googleApiUrl;

    @Value(value = "${google.api.keys}")
    private String googleApiKeys;


    public LocationView searchForLocation(String locationQuerySting) {
        String url = googleApiUrl + URLEncoder.encode(locationQuerySting, StandardCharsets.UTF_8) + "&key=" + googleApiKeys;
        ResponseEntity<GoogleResponse> body = restTemplate.getForEntity(url, GoogleResponse.class);

        String lat = body.getBody().getResults().get(0).getGeometry().getLocation().getLat();
        String lng = body.getBody().getResults().get(0).getGeometry().getLocation().getLng();

        if (repository.locationExists(lat, lng))
            return LocationMapper.getInstance().entityToView(repository.readLocation(lat, lng));

        LocationEntity location = LocationEntity.builder().addressDescription(locationQuerySting).latitude(lat).longitude(lng).build();
        repository.save(location);

        return LocationMapper.getInstance().entityToView(location);
    }

}
