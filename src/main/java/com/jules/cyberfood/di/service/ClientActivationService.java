package com.jules.cyberfood.di.service;

import com.jules.cyberfood.di.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ClientActivationService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void activate(Client client){
        client.activate();
        applicationEventPublisher.publishEvent(new ActivationClientEvent(client));
    }

}
