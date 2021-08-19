package com.jules.cyberfood.domain.service;

import com.jules.cyberfood.domain.exception.EntityNotFoundException;
import com.jules.cyberfood.domain.model.Kitchen;
import com.jules.cyberfood.domain.model.Restaurant;
import com.jules.cyberfood.domain.repository.KitchenRepository;
import com.jules.cyberfood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterRestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant){
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId);

        if (kitchen == null){
            throw new EntityNotFoundException(String.format("There is no kitchen %d.", kitchenId));
        }
        restaurant.setKitchen(kitchen);

        return restaurantRepository.addRestaurant(restaurant);
    }


}
