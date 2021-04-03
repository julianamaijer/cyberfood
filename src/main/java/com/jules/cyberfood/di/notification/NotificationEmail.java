package com.jules.cyberfood.di.notification;

import com.jules.cyberfood.di.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NotificationType(UrgencyLevel.NORMAL)
@Component
public class NotificationEmail implements Notification {

    @Autowired
    private NotifierProperties notifierProperties;

    @Override
    public void notifyClient(Client client, String message){
        System.out.println("Host: " + notifierProperties.getHostServer());
        System.out.println("Port: "  + notifierProperties.getPortServer());
        System.out.printf("Notifying %s by e-mail %s: %s\n", client.getName(), client.getEmail(), message);
    }

}
