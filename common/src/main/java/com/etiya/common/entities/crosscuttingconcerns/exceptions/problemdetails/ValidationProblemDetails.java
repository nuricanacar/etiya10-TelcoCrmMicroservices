package com.etiya.common.entities.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.entities.crosscuttingconcerns.exceptions.constants.ExceptionMessage;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ValidationProblemDetails extends ProblemDetails{
    private Map<String, String> validationErrors;

    public ValidationProblemDetails() {
        setTitle(ExceptionMessage.VALIDATION_ERROR);
        setType(ExceptionMessage.TYPE_VALIDATION);
        setStatus(HttpStatus.BAD_REQUEST.value());
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}