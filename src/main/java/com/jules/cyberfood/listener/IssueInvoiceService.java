package com.jules.cyberfood.listener;

import com.jules.cyberfood.di.service.ActivationClientEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class IssueInvoiceService {

    @EventListener
    public void clientActivationListener(ActivationClientEvent activationClientEvent){
        System.out.println("Issuing invoice to client " + activationClientEvent.getClient().getName());
    }
}
