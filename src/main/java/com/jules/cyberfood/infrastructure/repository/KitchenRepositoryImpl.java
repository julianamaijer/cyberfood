package com.jules.cyberfood.infrastructure.repository;

import com.jules.cyberfood.domain.model.Kitchen;
import com.jules.cyberfood.domain.repository.KitchenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Kitchen> allKitchens(){

        TypedQuery<Kitchen> query = entityManager.createQuery("from Kitchen", Kitchen.class);

        return query.getResultList();
    }

    @Override
    public Kitchen findById(Long id){
        return entityManager.find(Kitchen.class, id);
    }

    @Transactional
    @Override
    public Kitchen addKitchen(Kitchen kitchen){
        return entityManager.merge(kitchen);
    }

    @Transactional
    @Override
    public void removeKitchen(Kitchen kitchen){
        kitchen = findById(kitchen.getId());
        entityManager.remove(kitchen);
    }
}
