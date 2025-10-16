package com.etiya.searchservice.domain;

public class Address {
    private int addressId;
    private String street;
    private String houseNumber;
    private String description;
    private boolean isDefault;
    private int districtId;


    public Address() {
    }

    public Address(int id, String street, String houseNumber, String description, boolean isDefault, int districtId) {
        this.addressId = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.isDefault = isDefault;
        this.districtId = districtId;
    }

    public int getId() {
        return addressId;
    }

    public void setId(int id) {
        this.addressId = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }


}
