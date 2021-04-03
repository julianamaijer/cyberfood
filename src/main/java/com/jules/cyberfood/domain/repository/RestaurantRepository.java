package com.jules.cyberfood.domain.repository;

import com.jules.cyberfood.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> allRestaurants();
    Restaurant findById(Long id);
    Restaurant addRestaurant(Restaurant restaurant);
    void removeRestaurant(Restaurant restaurant);
}
