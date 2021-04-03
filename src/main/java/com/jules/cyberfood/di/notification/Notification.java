package com.jules.cyberfood.di.notification;

import com.jules.cyberfood.di.model.Client;

public interface Notification {
    void notifyClient(Client client, String message);
}
