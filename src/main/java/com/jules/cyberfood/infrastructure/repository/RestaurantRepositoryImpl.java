package com.jules.cyberfood.infrastructure.repository;

import com.jules.cyberfood.domain.model.Restaurant;
import com.jules.cyberfood.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Restaurant> allRestaurants() {
        TypedQuery<Restaurant> query = entityManager.createQuery("from Restaurant", Restaurant.class);
        return query.getResultList();
    }

    @Override
    public Restaurant findById(Long id) {
        return entityManager.find(Restaurant.class, id);
    }

    @Transactional
    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return entityManager.merge(restaurant);
    }

    @Transactional
    @Override
    public void removeRestaurant(Restaurant restaurant) {
        restaurant = findById(restaurant.getId());
        entityManager.remove(restaurant);
    }
}
