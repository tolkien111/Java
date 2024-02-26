package com.javafee.java.lessons.tasks.task2googleapi.service.dto.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationIdView {

    private UUID id;
    private String addressDescription;
}
