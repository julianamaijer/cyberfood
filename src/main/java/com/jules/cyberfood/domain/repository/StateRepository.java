package com.jules.cyberfood.domain.repository;

import com.jules.cyberfood.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> allStates();
    State findById(Long id);
    State addState(State state);
    void removeState(State state);

}
