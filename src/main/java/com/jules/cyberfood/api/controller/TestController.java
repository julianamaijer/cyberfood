package com.jules.cyberfood.api.controller;

import com.jules.cyberfood.domain.model.Kitchen;
import com.jules.cyberfood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping("/kitchens/by-name")
    public List<Kitchen> kitchensByName(String name){
        return kitchenRepository.findAllByName(name);
    }

    @GetMapping("/kitchens/unique-by-name")
    public Optional<Kitchen> kitchenByName(String name){
        return kitchenRepository.findByName(name);
    }
}
