package com.etiya.common.entities.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.entities.crosscuttingconcerns.exceptions.constants.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails() {
        setTitle(ExceptionMessage.BUSINESS_RULE_VIOLATION);
        setType(ExceptionMessage.TYPE_BUSINESS);
        setStatus(HttpStatus.BAD_REQUEST.value());
    }
}