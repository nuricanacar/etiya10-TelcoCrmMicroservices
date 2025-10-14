package com.etiya.common.entities.crosscuttingconcerns.exceptions.constants;

public class ExceptionMessage {

    //Business rule Messages
    public static final String BUSINESS_RULE_VIOLATION = "Business Rule Violation";

    //Problem Detail Types
    public static final String TYPE_BUSINESS = "https://example.com/probs/business";
    public static final String TYPE_VALIDATION = "https://example.com/probs/validation";


    //Internal Server Problem Detail Types
    public static final String INTERNAL_ERROR = "Internal server error";
    public static final String TYPE_INTERNAL = "https://example.com/probs/internal";


    //Validation Message
    public static final String VALIDATION_ERROR = "Validation rule violation";
    public static final String VALIDATION_ERRORS = "Validation errors";
}
