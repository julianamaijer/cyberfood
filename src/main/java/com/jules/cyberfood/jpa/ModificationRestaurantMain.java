package com.jules.cyberfood.jpa;

import com.jules.cyberfood.CyberfoodApplication;
import com.jules.cyberfood.domain.model.Restaurant;
import com.jules.cyberfood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ModificationRestaurantMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(CyberfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository modificationRestaurant = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant = modificationRestaurant.findById(2L);
        restaurant.setName("Cão véio");

        modificationRestaurant.addRestaurant(restaurant);

    }
}
