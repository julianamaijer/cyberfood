package com.jules.cyberfood.api.controller;

import com.jules.cyberfood.domain.exception.EntityInUseException;
import com.jules.cyberfood.domain.exception.EntityNotFoundException;
import com.jules.cyberfood.domain.model.State;
import com.jules.cyberfood.domain.repository.StateRepository;
import com.jules.cyberfood.domain.service.RegisterStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private RegisterStateService registerStateService;

    //método get lista:
    @GetMapping
    public List<State> listStates(){
        return stateRepository.allStates();
    }

    //método post:
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State saveState(@RequestBody State state){
        return registerStateService.save(state);
    }

    //método put:
    @PutMapping("/{stateId}")
    public ResponseEntity<State> updateState(@PathVariable Long stateId, @RequestBody State state){
        State thisState = stateRepository.findById(stateId);

        if (thisState != null){
            BeanUtils.copyProperties(state, thisState, "id");
            thisState = registerStateService.save(thisState);
            return ResponseEntity.ok(thisState);
        }
        return ResponseEntity.notFound().build();
    }

    //método delete:
    @DeleteMapping("/{stateId}")
    public ResponseEntity<State> deleteState(@PathVariable Long stateId){
        try {
            registerStateService.delete(stateId);
            return ResponseEntity.noContent().build();
        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }
}
