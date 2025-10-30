package com.etiya.customerservice.service.abstracts;

import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;

import java.util.List;

public interface CityService {
    void add(City city);
    List<GetListCityResponse> getAll();
    City existsById(int id);
}
