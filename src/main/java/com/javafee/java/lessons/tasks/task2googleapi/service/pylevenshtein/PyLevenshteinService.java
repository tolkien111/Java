package com.javafee.java.lessons.tasks.task2googleapi.service.pylevenshtein;

import com.javafee.java.lessons.tasks.task2googleapi.repository.LocationRepository;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationIdView;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.mapper.LocationMapper;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
public class PyLevenshteinService {

    @NonNull
    private final LocationRepository repository;
    @NotNull
    private final LocationMapper mapper;

    public List<LocationIdView> searchSimilarLocations(String locationQueryString) {
        List<LocationIdView> candidateLocations = new ArrayList<>(repository.readAllLocations().stream()
                .map(mapper::entityToIdView)
                .filter(location -> isSimilar(locationQueryString, location.getAddressDescription()))
                .toList());

        if(!candidateLocations.isEmpty()){
            candidateLocations.add(new LocationIdView(UUID.randomUUID(),
                    "I did not found my location on the list"));
        }
        return candidateLocations;
    }

    private boolean isSimilar(String locationQueryString, String existingAddressDescription) {
        ProcessBuilder processBuilder = new ProcessBuilder("python",
                "C:\\Users\\kamil\\git\\Java\\src\\main\\resources\\levenshtein.py",
                locationQueryString,
                existingAddressDescription); //TODO only for me, change for use on all computers
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String result = reader.readLine();
                process.waitFor();
                return Boolean.parseBoolean(result);
            }
        } catch (IOException e) {
            // Obsługa IOException
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