package com.javafee.java.lessons.tasks.task2googleapi.service.dto.exception;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class CustomExceptionDto {

    private Timestamp timestamp;
    private String status;
    private String message;
    private String path;
    private String method;

}
