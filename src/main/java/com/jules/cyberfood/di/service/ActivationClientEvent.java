package com.jules.cyberfood.di.service;

import com.jules.cyberfood.di.model.Client;

public class ActivationClientEvent {

    private Client client;

    public ActivationClientEvent(Client client){
        super();
        this.client=client;
    }

    public Client getClient() {
        return client;
    }
}
