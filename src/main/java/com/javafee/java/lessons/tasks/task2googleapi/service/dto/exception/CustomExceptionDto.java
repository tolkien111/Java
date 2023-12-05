package com.javafee.java.lessons.tasks.task2googleapi.service.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CustomExceptionDto {

    private Timestamp timestamp;
    private String status;
    private String message;
    private String path;
    private String method;

}
