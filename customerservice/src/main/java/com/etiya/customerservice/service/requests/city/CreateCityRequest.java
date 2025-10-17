package com.etiya.customerservice.service.requests.city;

import com.etiya.customerservice.service.messages.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateCityRequest {
    @NotBlank(message = Messages.CityNameRequired)
    @Pattern(regexp = "^[a-zA-ZçÇşŞğĞıİüÜöÖ]+", message = Messages.CityNamePattern)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateCityRequest(String name) {
        this.name = name;
    }

    public CreateCityRequest() {
    }
}
