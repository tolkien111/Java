package com.javafee.java.lessons.tasks.task2googleapi.controller;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.exception.CustomExceptionDto;
import com.javafee.java.lessons.tasks.task2googleapi.service.dto.location.LocationResponseView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ImplGeoControllerAPITest extends CucumberSpringConfiguration {

    @Given("the client has location query for {string}")
    public void the_client_has_location_query(String locationQueryString) {
        this.locationQueryString = locationQueryString;
    }

    @When("the client requests the location data")
    public void the_client_requests_the_location_data() {
        this.response = restTemplate.getForEntity("/api/geo/location/" + locationQueryString, LocationResponseView.class);
        this.exceptionResponse = restTemplate.getForEntity("/api/geo/location/" + locationQueryString,
                CustomExceptionDto.class);
    }

    @Then("the client should receives coordinates {string} and {string}")
    public void the_client_should_receive_coordinates_for_address(String latitude, String longitude) {
        assertEquals(Objects.requireNonNull(response.getBody()).getLocationView().getLatitude(), latitude);
        assertEquals(Objects.requireNonNull(response.getBody()).getLocationView().getLongitude(), longitude);
    }

    @Then("the client should receives an error indicating a problem with Google API")
    public void the_client_should_receives_an_error_indicating_a_problem_with_google_api() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Objects.requireNonNull(exceptionResponse.getBody()).getMessage(),
                "Google API returned no results for the query: " + locationQueryString);
    }

    @Then("the client should receives an error indicating a problem with location query string")
    public void the_client_should_receives_an_error_indicating_a_problem_with_location_query_string() {
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(Objects.requireNonNull(exceptionResponse.getBody()).getMessage()
                .contains("locationQueryString contains forbidden character: %"));
    }
}