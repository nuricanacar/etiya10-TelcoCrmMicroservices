package com.etiya.customerservice.service.requests.individualCustomer;

import com.etiya.customerservice.service.messages.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public class CreateIndividualCustomerRequest {

    @NotBlank(message = Messages.FirstNameRequired)
    @Size(min = 2,max = 50,message = Messages.FirstNameSize)
    private String firstName;

    private String lastName;

    private String middleName;

    @NotBlank(message = Messages.NatIdRequired)
    @Size(min = 11,max = 11,message = Messages.NatIdSize)
    @Pattern(regexp = "^[0-9]+$",message = Messages.NatIdPattern)
    private String nationalId;

    private String dateOfBirth;

    private String motherName;

    private String fatherName;

    private String gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CreateIndividualCustomerRequest(String firstName, String lastName, String middleName, String nationalId, String dateOfBirth, String motherName, String fatherName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.gender = gender;
    }

    public CreateIndividualCustomerRequest() {
    }
}