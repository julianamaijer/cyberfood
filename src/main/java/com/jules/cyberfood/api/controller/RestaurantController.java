package com.jules.cyberfood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jules.cyberfood.domain.exception.EntityNotFoundException;
import com.jules.cyberfood.domain.model.Kitchen;
import com.jules.cyberfood.domain.model.Restaurant;
import com.jules.cyberfood.domain.repository.KitchenRepository;
import com.jules.cyberfood.domain.repository.RestaurantRepository;
import com.jules.cyberfood.domain.service.RegisterKitchenService;
import com.jules.cyberfood.domain.service.RegisterRestaurantService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RegisterRestaurantService registerRestaurantService;

    @GetMapping
    public List<Restaurant> listRestaurants(){
        return restaurantRepository.allRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> fetchRestaurant(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> saveRestaurant(@RequestBody Restaurant restaurant){
        try {
            restaurant = registerRestaurantService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant){
        try {
            Restaurant thisRestaurant = restaurantRepository.findById(restaurantId);
            if(thisRestaurant != null){
                BeanUtils.copyProperties(restaurant, thisRestaurant, "id");
                thisRestaurant = registerRestaurantService.save(thisRestaurant);
                return ResponseEntity.ok(thisRestaurant);
            }
            return ResponseEntity.notFound().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields){
        Restaurant thisRestaurant = restaurantRepository.findById(restaurantId);

        if (thisRestaurant == null){
            return ResponseEntity.notFound().build();
        }

        merge(fields, thisRestaurant);

        return updateRestaurant(restaurantId, thisRestaurant);
    }

    private void merge(Map<String, Object> originFields, Restaurant targetRestaurant){
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant originRestaurant = objectMapper.convertValue(originFields, Restaurant.class);

        originFields.forEach((propertyName, propertyValue) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, originRestaurant);

            System.out.println(propertyName + " = " + propertyValue);

            ReflectionUtils.setField(field, targetRestaurant, newValue);
        });
    }
}
