package com.javafee.java.lessons.tasks.task2googleapi.controller;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.exception.CustomExceptionDto;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationView;
import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.util.DefaultUriBuilderFactory;

@CucumberContextConfiguration
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSpringConfiguration {

    @LocalServerPort
    private int port;

    protected TestRestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new TestRestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));
    }
    protected MockRestServiceServer mockServer;
    protected String locationQueryString;
    protected ResponseEntity<LocationView> response;
    protected ResponseEntity<CustomExceptionDto> exceptionResponse;
}
