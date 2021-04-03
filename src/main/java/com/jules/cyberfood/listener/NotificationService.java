package com.jules.cyberfood.listener;

import com.jules.cyberfood.di.notification.Notification;
import com.jules.cyberfood.di.notification.NotificationType;
import com.jules.cyberfood.di.notification.UrgencyLevel;
import com.jules.cyberfood.di.service.ActivationClientEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    @NotificationType(UrgencyLevel.NORMAL)
    @Autowired
    private Notification notification;

    @EventListener
    public void clientActivatedListener(ActivationClientEvent activationClientEvent){
        notification.notifyClient(activationClientEvent.getClient(), "Your register in the system is activated.");
    }
}
