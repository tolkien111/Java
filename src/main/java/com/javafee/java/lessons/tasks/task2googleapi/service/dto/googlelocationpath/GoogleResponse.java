package com.javafee.java.lessons.tasks.task2googleapi.service.dto.googlelocationpath;

import lombok.Data;

import java.util.List;

@Data
public class GoogleResponse {

    private List<Result> results;
    private String status;

}
