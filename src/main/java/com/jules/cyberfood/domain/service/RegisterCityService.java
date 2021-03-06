package com.jules.cyberfood.domain.service;

import com.jules.cyberfood.domain.exception.EntityInUseException;
import com.jules.cyberfood.domain.exception.EntityNotFoundException;
import com.jules.cyberfood.domain.model.City;
import com.jules.cyberfood.domain.model.State;
import com.jules.cyberfood.domain.repository.CityRepository;
import com.jules.cyberfood.domain.repository.StateRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterCityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city){
        Long id = city.getState().getId();
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("State %d doesn't exist.", id)));

        city.setState(state);

        return cityRepository.save(city);
    }

    public void delete(Long id){
        try {
            cityRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e ){
            throw new EntityNotFoundException(String.format("City %d doesn't exist.", id));
        }catch (DataIntegrityViolationException e){
            throw new EntityInUseException("City %d can't be removed because  it's in use.");
        }

    }
}
