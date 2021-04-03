package com.jules.cyberfood.jpa;

import com.jules.cyberfood.CyberfoodApplication;
import com.jules.cyberfood.domain.model.Restaurant;
import com.jules.cyberfood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class InclusionRestaurantMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(CyberfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository inclusionRestaurant = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Pizza Hut");
        restaurant.setTaxShipping(BigDecimal.valueOf(5.00));

        inclusionRestaurant.addRestaurant(restaurant);

    }
}
