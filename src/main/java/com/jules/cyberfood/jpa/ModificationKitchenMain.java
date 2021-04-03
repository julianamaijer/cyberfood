package com.jules.cyberfood.jpa;

import com.jules.cyberfood.CyberfoodApplication;
import com.jules.cyberfood.domain.model.Kitchen;
import com.jules.cyberfood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ModificationKitchenMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(CyberfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository registerKitchen = applicationContext.getBean(KitchenRepository.class);

        Kitchen kitchen = new Kitchen();
        kitchen.setId(1L);
        kitchen.setName("Brazilian");

        registerKitchen.addKitchen(kitchen);

    }

}
