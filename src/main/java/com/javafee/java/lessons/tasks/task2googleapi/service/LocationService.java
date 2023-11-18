package com.javafee.java.lessons.tasks.task2googleapi.service;


import com.javafee.java.lessons.tasks.task2googleapi.entity.LocationEntity;
import com.javafee.java.lessons.tasks.task2googleapi.repository.LocationRepository;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationView;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath.GoogleResponse;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.mapper.LocationMapper;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.GoogleApiResponseValidator;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.LocationEntityCoordinatesValidator;
import com.javafee.java.lessons.tasks.task2googleapi.service.validation.LocationQueryStringValidator;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {

    @NonNull
    private final LocationRepository repository;
    @NonNull
    private final RestTemplate restTemplate;
    @NonNull
    private final LocationMapper mapper;
    @NonNull
    private final LocationQueryStringValidator validator;

    @Value(value = "${google.api.url}")
    private String googleApiUrl;
    @Value(value = "${google.api.keys}")
    private String googleApiKeys;

    public LocationView searchForLocation(String locationQueryString) {
        validator.validateLocalQueryString(locationQueryString);
        GoogleResponse body = getGoogleResponseBody(locationQueryString);
        GoogleApiResponseValidator.validateGoogleApiResponse(body, locationQueryString);
        String latitude = body.getResults().get(0).getGeometry().getLocation().getLat(),
                longitude = body.getResults().get(0).getGeometry().getLocation().getLng();
        LocationEntityCoordinatesValidator.validateLocationCoordinatesEntity(latitude, longitude);
        if (repository.locationExists(latitude, longitude))
            return mapper.entityToView(repository.readLocation(latitude, longitude));
        repository.save(createLocationEntity(body, latitude, longitude));
        return mapper.entityToView(createLocationEntity(body, latitude, longitude));
    }

    private String createGoogleApiUrl(String locationQueryString) {
        return googleApiUrl + locationQueryString + "&key=" + googleApiKeys;
    }

    private GoogleResponse getGoogleResponseBody(String locationQueryString) {
        return restTemplate.getForEntity(createGoogleApiUrl(locationQueryString), GoogleResponse.class).getBody();
    }

    private String createFormattedAddress(GoogleResponse body) {
        return body.getResults().get(0).getFormatted_address();
    }

    private LocationEntity createLocationEntity(GoogleResponse body, String latitude, String longitude) {
        return LocationEntity.builder()
                .addressDescription(createFormattedAddress(body))
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}