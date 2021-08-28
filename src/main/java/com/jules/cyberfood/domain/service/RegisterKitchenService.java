package com.jules.cyberfood.domain.service;

import com.jules.cyberfood.domain.exception.EntityInUseException;
import com.jules.cyberfood.domain.exception.EntityNotFoundException;
import com.jules.cyberfood.domain.model.Kitchen;
import com.jules.cyberfood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterKitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    //it creates and updates the kitchen
    public Kitchen save(Kitchen kitchen){
        return kitchenRepository.save(kitchen);
    }

    public void exclude(Long kitchenId){
        try {
            kitchenRepository.deleteById(kitchenId);
        }catch (EmptyResultDataAccessException e ){
            throw new EntityNotFoundException(String.format("Kitchen %d doesn't exist.", kitchenId));
        }catch (DataIntegrityViolationException e){
            throw new EntityInUseException(String.format("Kitchen %d can't be removed, because it's in use.", kitchenId));
        }
    }
}
