package com.etiya.customerservice.service.responses.city;

import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.service.responses.district.GetListDistrictResponse;

import java.util.List;

public class GetListCityResponse {
    private int id;
    private String name;
    private List<GetListDistrictResponse> districts;

    public List<GetListDistrictResponse> getDistricts() {
        return districts;
    }

    public void setDistricts(List<GetListDistrictResponse> districts) {
        this.districts = districts;
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

    public GetListCityResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GetListCityResponse() {
    }
}
