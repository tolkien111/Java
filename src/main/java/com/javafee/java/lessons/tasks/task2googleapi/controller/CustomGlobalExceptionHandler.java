package com.javafee.java.lessons.tasks.task2googleapi.controller;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.exception.CustomExceptionDto;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.GoogleCommunicationException;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.LocationQueryStringException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(LocationQueryStringException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomExceptionDto> handleError(LocationQueryStringException l, WebRequest webRequest, HttpServletRequest request) {
        log.error("Location query string exception: {}", l.getMessage());
        return new ResponseEntity<>(getExceptionDto(HttpStatus.BAD_REQUEST, l.getMessage(), webRequest, request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GoogleCommunicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomExceptionDto> handleError(GoogleCommunicationException g, WebRequest webRequest, HttpServletRequest request) {
        log.error("Google communication exception: {}", g.getMessage());
        return new ResponseEntity<>(getExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, g.getMessage(), webRequest, request),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static CustomExceptionDto getExceptionDto(HttpStatus internalServerError,
                                                      String g,
                                                      WebRequest webRequest,
                                                      HttpServletRequest request) {
        return CustomExceptionDto.builder()
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .status(String.valueOf(internalServerError))
                .message(g)
                .path(webRequest.getDescription(false))
                .method(request.getMethod())
                .build();
    }
}
