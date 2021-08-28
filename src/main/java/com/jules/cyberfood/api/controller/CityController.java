package com.jules.cyberfood.api.controller;


import com.jules.cyberfood.domain.exception.EntityInUseException;
import com.jules.cyberfood.domain.exception.EntityNotFoundException;
import com.jules.cyberfood.domain.model.City;
import com.jules.cyberfood.domain.repository.CityRepository;
import com.jules.cyberfood.domain.service.RegisterCityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegisterCityService registerCityService;

    @GetMapping
    public List<City> listCities(){
        return cityRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity(@RequestBody City city){
        return registerCityService.save(city);
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> updateCity(@PathVariable Long cityId, @RequestBody City city) {

        try{
            Optional<City> thisCity = cityRepository.findById(cityId);
            if(thisCity.isPresent()){
                BeanUtils.copyProperties(city, thisCity.get(), "id");
                City saveCity = registerCityService.save(thisCity.get());
                return ResponseEntity.ok(saveCity);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> deleteCity(@PathVariable Long cityId){
        try{
            registerCityService.delete(cityId);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
