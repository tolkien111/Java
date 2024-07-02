package com.javafee.java.lessons.tasks.task2googleapi.service;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationResponseView;

public interface LocationService {
    LocationResponseView searchForLocation(String locationQueryString);
}
