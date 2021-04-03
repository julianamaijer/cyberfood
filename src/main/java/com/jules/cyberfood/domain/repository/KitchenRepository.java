package com.jules.cyberfood.domain.repository;

import com.jules.cyberfood.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> allKitchens();
    Kitchen findById(Long id);
    Kitchen addKitchen(Kitchen kitchen);
    void removeKitchen(Kitchen kitchen);

}
