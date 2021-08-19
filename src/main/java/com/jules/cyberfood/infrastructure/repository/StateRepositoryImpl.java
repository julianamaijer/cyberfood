package com.jules.cyberfood.infrastructure.repository;

import com.jules.cyberfood.domain.model.State;
import com.jules.cyberfood.domain.repository.StateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<State> allStates() {
        TypedQuery<State> query = entityManager.createQuery("from State", State.class);
        return query.getResultList();
    }

    @Override
    public State findById(Long id) {
        return entityManager.find(State.class, id);
    }

    @Transactional
    @Override
    public State saveState(State state) {
        return entityManager.merge(state);
    }

    @Transactional
    @Override
    public void removeState(Long id) {
        State state = findById(id);

        if (state == null){
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(state);
    }
}
