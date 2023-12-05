package com.javafee.java.lessons.tasks.task2googleapi.controller;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "com.javafee.java.lessons.tasks.task2googleapi.controller")
public class CucumberConfigurationTest {
}