package io.jyotirmay.digitaltwin.cloudapi.v1.model;

import java.io.Serializable;

public class MqttClient implements Serializable {

    private final String clientId;

    private final boolean cleanSession;

    private final String username;

    private final String password;

    private final String lastWillTopic;

    private final String lastWillQos;

    private final String lastWillMessage;

    private final boolean lastWillRetain;

    private final int keepAlive;

    public MqttClient(String clientId, boolean cleanSession, String username, String password,
                      String lastWillTopic, String lastWillQos, String lastWillMessage,
                      boolean lastWillRetain, int keepAlive) {
        this.clientId = clientId;
        this.cleanSession = cleanSession;
        this.username = username;
        this.password = password;
        this.lastWillTopic = lastWillTopic;
        this.lastWillQos = lastWillQos;
        this.lastWillMessage = lastWillMessage;
        this.lastWillRetain = lastWillRetain;
        this.keepAlive = keepAlive;
    }

    public String getClientId() {
        return clientId;
    }

    public boolean isCleanSession() {
        return cleanSession;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLastWillTopic() {
        return lastWillTopic;
    }

    public String getLastWillQos() {
        return lastWillQos;
    }

    public String getLastWillMessage() {
        return lastWillMessage;
    }

    public boolean isLastWillRetain() {
        return lastWillRetain;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    @Override
    public String toString() {
        return "MqttClient{" +
                "clientId='" + clientId + '\'' +
                ", cleanSession=" + cleanSession +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastWillTopic='" + lastWillTopic + '\'' +
                ", lastWillQos='" + lastWillQos + '\'' +
                ", lastWillMessage='" + lastWillMessage + '\'' +
                ", lastWillRetain=" + lastWillRetain +
                ", keepAlive=" + keepAlive +
                '}';
    }
}
