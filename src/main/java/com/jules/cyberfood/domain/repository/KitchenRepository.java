package com.jules.cyberfood.domain.repository;

import com.jules.cyberfood.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> allKitchens();
    Kitchen findById(Long id);
    Kitchen saveKitchen(Kitchen kitchen);
    void removeKitchen(Long id);

}
