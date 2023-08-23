package com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath;

import lombok.Data;

@Data
public class Result {

    private Geometry geometry;
    private String formatted_address;

}
