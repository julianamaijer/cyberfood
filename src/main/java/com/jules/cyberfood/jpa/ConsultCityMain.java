package com.jules.cyberfood.jpa;

import com.jules.cyberfood.CyberfoodApplication;
import com.jules.cyberfood.domain.model.City;
import com.jules.cyberfood.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultCityMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(CyberfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository consultCity = applicationContext.getBean(CityRepository.class);
        List<City> cities = consultCity.allCities();

        for (City city : cities){
            System.out.println(city.getName());
        }
    }
}
