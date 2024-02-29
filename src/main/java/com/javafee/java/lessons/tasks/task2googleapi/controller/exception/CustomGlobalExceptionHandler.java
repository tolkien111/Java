package com.javafee.java.lessons.tasks.task2googleapi.controller.exception;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.exception.CustomExceptionDto;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.GoogleCommunicationException;
import com.javafee.java.lessons.tasks.task2googleapi.service.exception.LocationEntityException;
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
    public ResponseEntity<CustomExceptionDto> handleError(LocationQueryStringException exception, WebRequest webRequest,
                                                          HttpServletRequest request) {
        log.error("Location query string exception: {}", exception.getMessage());
        return new ResponseEntity<>(getExceptionDto(HttpStatus.BAD_REQUEST, exception.getMessage(), webRequest, request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocationEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomExceptionDto> handleError(LocationEntityException exception, WebRequest webRequest,
                                                          HttpServletRequest request) {
        log.error("Location coordinates exception: {}", exception.getMessage());
        return new ResponseEntity<>(getExceptionDto(HttpStatus.NOT_FOUND, exception.getMessage(), webRequest, request),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GoogleCommunicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomExceptionDto> handleError(GoogleCommunicationException exception, WebRequest webRequest,
                                                          HttpServletRequest request) {
        log.error("Google communication exception: {}", exception.getMessage());
        return new ResponseEntity<>(getExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), webRequest, request),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static CustomExceptionDto getExceptionDto(HttpStatus internalServerError, String message,
                                                      WebRequest webRequest, HttpServletRequest request) {
        return CustomExceptionDto.builder()
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .status(String.valueOf(internalServerError))
                .message(message)
                .path(webRequest.getDescription(false))
                .method(request.getMethod())
                .build();
    }
}
