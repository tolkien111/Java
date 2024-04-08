package com.javafee.java.lessons.tasks.task2googleapi.service.pylevenshtein;

import com.javafee.java.lessons.tasks.task2googleapi.repository.LocationRepository;
import com.javafee.java.lessons.tasks.task2googleapi.service.LocationService;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationResponseView;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.mapper.LocationMapper;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class QueryPyLevenshteinService {

    @NonNull
    private final LocationService locationService;
    @NonNull
    private final LocationRepository repository;
    @NotNull
    private final LocationMapper mapper;
    @Value(value = "${sample.id}")
    private String sampleId;

    public LocationResponseView getLocationResponseView(UUID id, HttpSession session) {
        if (Objects.equals(id.toString(), sampleId)) {
            String locationQueryStringSession = (String) session.getAttribute("locationQueryString");
            session.invalidate();//user can use only one time LQS, for one click
            return locationService.searchForLocation((locationQueryStringSession));
        }
        session.invalidate();
        return new LocationResponseView(mapper.entityToView(repository.searchById(id)));
    }
}