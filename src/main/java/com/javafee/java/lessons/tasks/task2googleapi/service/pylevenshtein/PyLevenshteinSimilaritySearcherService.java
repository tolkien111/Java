package com.javafee.java.lessons.tasks.task2googleapi.service.pylevenshtein;

import com.javafee.java.lessons.tasks.task2googleapi.repository.LocationRepository;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationIdView;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.mapper.LocationMapper;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@Transactional
@RequiredArgsConstructor
public class PyLevenshteinSimilaritySearcherService {

    @NonNull
    private final LocationRepository repository;
    @NotNull
    private final LocationMapper mapper;
    @Value("${sample.id}")
    private String sampleId;

    public List<LocationIdView> searchSimilarLocations(String locationQueryString) {
        List<LocationIdView> candidateLocations = new ArrayList<>(repository.readAllLocations().stream()
                .map(mapper::entityToIdView)
                .filter(location -> isSimilar(locationQueryString, location.getAddressDescription()))
                .toList());
        isNotEmptyList(candidateLocations);
        return candidateLocations;
    }

    private void isNotEmptyList(List<LocationIdView> candidateLocations) {
        if (!candidateLocations.isEmpty()) {
            candidateLocations.add(createNotFoundOption());
        }
    }

    @NotNull
    private LocationIdView createNotFoundOption() {
        return new LocationIdView(UUID.fromString(sampleId),
                "I did not found my location on the list");
    }

    private boolean isSimilar(String locationQueryString, String existingAddressDescription) {
        ProcessBuilder processBuilder = getProcessBuilder(locationQueryString, existingAddressDescription);
        processBuilder.redirectErrorStream(true);
        return stringsAreSimilar(processBuilder);
    }

    @NotNull
    private static ProcessBuilder getProcessBuilder(String locationQueryString, String existingAddressDescription) {
        return new ProcessBuilder("python",
                "C:\\Users\\kamil\\git\\Java\\src\\main\\resources\\levenshtein.py",
                locationQueryString,
                existingAddressDescription); //TODO only for me, change to use on all desktops
    }

    private static boolean stringsAreSimilar(ProcessBuilder processBuilder) {
        try {
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String result = reader.readLine();
                process.waitFor();
                return Boolean.parseBoolean(result);
            }
        } catch (IOException e) {
            Logger.getLogger("File not found");
            e.printStackTrace(); //to improve
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.getLogger("Unexpected interrupt error");
            e.printStackTrace(); //to improve
            return false;
        }
    }
}