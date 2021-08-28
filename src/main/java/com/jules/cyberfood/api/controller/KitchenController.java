package com.jules.cyberfood.api.controller;

import com.jules.cyberfood.domain.exception.EntityInUseException;
import com.jules.cyberfood.domain.exception.EntityNotFoundException;
import com.jules.cyberfood.domain.model.Kitchen;
import com.jules.cyberfood.domain.repository.KitchenRepository;
import com.jules.cyberfood.domain.service.RegisterKitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RegisterKitchenService registerKitchenService;

    @GetMapping
    public List<Kitchen> listKitchen(){
        return kitchenRepository.findAll();
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> fetchKitchen(@PathVariable Long kitchenId) {
        Optional<Kitchen> kitchen = kitchenRepository.findById(kitchenId);

        if(kitchen.isPresent()){
            return ResponseEntity.ok(kitchen.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen saveKitchen(@RequestBody Kitchen kitchen){
        return registerKitchenService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> updateKitchen(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen){
        Optional<Kitchen> thisKitchen = kitchenRepository.findById(kitchenId);

        if (thisKitchen.isPresent()){
            BeanUtils.copyProperties(kitchen, thisKitchen.get(), "id");
            Kitchen saveKitchen = registerKitchenService.save(thisKitchen.get());
            return ResponseEntity.ok(saveKitchen);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> removeKitchen(@PathVariable Long kitchenId){
        try {
            registerKitchenService.exclude(kitchenId);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
