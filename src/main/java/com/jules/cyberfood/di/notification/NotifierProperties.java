package com.jules.cyberfood.di.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notifier.email")
public class NotifierProperties {

    /**
     * Host do servidor de e-mail
     */
    private String hostServer;

    /**
     * Porta do Servidor de e-mail
     */
    private Integer portServer;

    public Integer getPortServer() {
        return portServer;
    }

    public String getHostServer() {
        return hostServer;
    }

    public void setHostServer(String hostServer) {
        this.hostServer = hostServer;
    }

    public void setPortServer(Integer portServer) {
        this.portServer = portServer;
    }
}
