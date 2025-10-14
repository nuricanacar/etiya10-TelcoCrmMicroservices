package com.etiya.customerservice.service.concretes;

import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.repository.CityRepository;
import com.etiya.customerservice.service.abstracts.CityService;
import com.etiya.customerservice.service.rules.CityBusinessRules;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityBusinessRules cityBusinessRules;

    public CityServiceImpl(CityRepository cityRepository, CityBusinessRules cityBusinessRules) {
        this.cityRepository = cityRepository;
        this.cityBusinessRules = cityBusinessRules;
    }

    @Override
    public void add(City city) {
        cityRepository.save(city);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City existsById(int id) {
        cityBusinessRules.checkIfCityExists(id);
        return cityRepository.findById(id).get();
    }
}
