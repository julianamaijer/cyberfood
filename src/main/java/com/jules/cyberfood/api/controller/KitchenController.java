package com.jules.cyberfood.api.controller;

import com.jules.cyberfood.api.model.KitchensXmlWrapper;
import com.jules.cyberfood.domain.exception.EntityInUseException;
import com.jules.cyberfood.domain.exception.EntityNotFoundException;
import com.jules.cyberfood.domain.model.Kitchen;
import com.jules.cyberfood.domain.repository.KitchenRepository;
import com.jules.cyberfood.domain.service.RegisterKitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RegisterKitchenService registerKitchenService;

    @GetMapping
    public List<Kitchen> listKitchen(){
        return kitchenRepository.allKitchens();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXmlWrapper listXml(){
        return new KitchensXmlWrapper(kitchenRepository.allKitchens());
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> fetchKitchen(@PathVariable Long kitchenId) {
        Kitchen kitchen = kitchenRepository.findById(kitchenId);
        //return ResponseEntity.status(HttpStatus.OK).body(kitchen);

        if(kitchen != null){
            return ResponseEntity.ok(kitchen);
        }
        return ResponseEntity.notFound().build();

/*        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://local:8080/kitchens");

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .headers(headers)
                .build();
    }*/
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen saveKitchen(@RequestBody Kitchen kitchen){
        return registerKitchenService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> updateKitchen(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen){
        Kitchen thisKitchen = kitchenRepository.findById(kitchenId);

        if (thisKitchen != null){
            //thisKitchen.setName(kitchen.getName());
            BeanUtils.copyProperties(kitchen, thisKitchen, "id");
            thisKitchen = registerKitchenService.save(thisKitchen);
            return ResponseEntity.ok(thisKitchen);
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
