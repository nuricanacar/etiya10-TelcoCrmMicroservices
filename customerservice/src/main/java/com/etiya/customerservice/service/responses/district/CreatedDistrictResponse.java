package com.etiya.customerservice.service.responses.district;

public class CreatedDistrictResponse {

    private int id;
    private String name;
    private int cityId;

    public CreatedDistrictResponse() {
    }

    public CreatedDistrictResponse(int cityId, String name, int id) {
        this.cityId = cityId;
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}