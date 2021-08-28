package com.jules.cyberfood.domain.service;

import com.jules.cyberfood.domain.exception.EntityInUseException;
import com.jules.cyberfood.domain.exception.EntityNotFoundException;
import com.jules.cyberfood.domain.model.State;
import com.jules.cyberfood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterStateService {

    @Autowired
    private StateRepository stateRepository;

    public State save(State state){
        return stateRepository.save(state);
        //entity not found
    }

    public void delete(Long id){
        try {
            stateRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new EntityInUseException(String.format("State %d can't be removed because it's in use.", id));
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(String.format("State %d doesn't exist.", id));
        }

    }

}
