package com.javafee.java.lessons.tasks.task2googleapi.service.exception;

public abstract class BusinessServiceException extends RuntimeException{

    public BusinessServiceException(String message) {
        super(message);
    }

}
