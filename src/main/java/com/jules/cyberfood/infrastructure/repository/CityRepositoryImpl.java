package com.jules.cyberfood.infrastructure.repository;

import com.jules.cyberfood.domain.model.City;
import com.jules.cyberfood.domain.repository.CityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<City> allCities() {
        TypedQuery<City> query = entityManager.createQuery("from City", City.class);

        return query.getResultList();
    }

    @Override
    public City findById(Long id) {
        return entityManager.find(City.class, id);
    }

    @Override
    @Transactional
    public City addCity(City city) {
        return entityManager.merge(city);
    }

    @Override
    @Transactional
    public void removeCity(Long id) {
        City city = findById(id);

        if (city == null){
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(city);
    }
}
