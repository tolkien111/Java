package com.javafee.java.lessons.tasks.task2googleapi.service.dto.location;

import lombok.Data;

import java.util.UUID;

@Data
public class LocationIdView {

    private UUID id;
    private String addressDescription;
}
