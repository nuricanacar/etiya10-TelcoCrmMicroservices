package com.etiya.customerservice.service.abstracts;

import com.etiya.customerservice.domain.entities.City;

import java.util.List;

public interface CityService {
    void add(City city);
    List<City> getAll();
    City existsById(int id);
}
