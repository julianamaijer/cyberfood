package com.jules.cyberfood.jpa;

import com.jules.cyberfood.CyberfoodApplication;
import com.jules.cyberfood.domain.model.Restaurant;
import com.jules.cyberfood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultRestaurantMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(CyberfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository consultRestaurant = applicationContext.getBean(RestaurantRepository.class);
        List<Restaurant> restaurants = consultRestaurant.allRestaurants();

        for (Restaurant restaurant : restaurants){
            System.out.println(restaurant.getName() + ", " + restaurant.getTaxShipping());
        }
    }
}
