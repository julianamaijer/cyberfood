package com.jules.cyberfood.infrastructure.repository;

import com.jules.cyberfood.domain.model.State;
import com.jules.cyberfood.domain.repository.StateRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<State> allStates() {
        TypedQuery<State> query = entityManager.createQuery("from State", State.class);
        return query.getResultList();
    }

    @Override
    public State findById(Long id) {
        return entityManager.find(State.class, id);
    }

    @Override
    public State addState(State state) {
        return entityManager.merge(state);
    }

    @Override
    public void removeState(State state) {
        state = findById(state.getId());
        entityManager.remove(state);
    }
}
