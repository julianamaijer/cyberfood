package com.jules.cyberfood.domain.repository;

import com.jules.cyberfood.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> allCities();
    City findById(Long id);
    City addCity(City city);
    void removeCity(Long id);

}
