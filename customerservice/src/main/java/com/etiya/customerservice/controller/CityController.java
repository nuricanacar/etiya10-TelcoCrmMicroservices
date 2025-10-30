package com.etiya.customerservice.controller;

import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.service.abstracts.CityService;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody City city) {
        cityService.add(city);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCityResponse> getAll() {
        return cityService.getAll();
    }
}