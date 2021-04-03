package com.jules.cyberfood.di.notification;

import com.jules.cyberfood.di.model.Client;
import org.springframework.stereotype.Component;

@NotificationType(UrgencyLevel.URGENT)
@Component
public class NotificationSMS implements Notification {

    @Override
    public void notifyClient(Client client, String message){
        System.out.printf("Notifying %s by SMS to phone number %s: %s\n", client.getName(), client.getPhone(), message);
    }

}
