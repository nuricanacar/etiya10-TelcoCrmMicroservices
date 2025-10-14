package com.etiya.common.entities.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.entities.crosscuttingconcerns.exceptions.constants.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class InternalServerProblemDetails extends ProblemDetails{
    public InternalServerProblemDetails() {
        setTitle(ExceptionMessage.INTERNAL_ERROR);
        setType(ExceptionMessage.TYPE_BUSINESS);
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
