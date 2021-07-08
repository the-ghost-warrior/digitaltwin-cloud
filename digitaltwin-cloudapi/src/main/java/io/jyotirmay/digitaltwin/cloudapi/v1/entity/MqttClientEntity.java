package io.jyotirmay.digitaltwin.cloudapi.v1.entity;

import io.jyotirmay.digitaltwin.cloudapi.v1.model.MqttClient;

import java.io.Serializable;
import java.util.Date;


public class MqttClientEntity implements Serializable {

    private Long id;

    private final String clientId;

    private final boolean cleanSession;

    private final String username;

    private final String password;

    private final String lastWillTopic;

    private final int lastWillQos;

    private final String lastWillMessage;

    private final boolean lastWillRetain;

    private final int keepAlive;

    private Date createdOn;

    private Date updatedOn;

    private boolean active;

    public MqttClientEntity(String clientId, boolean cleanSession, String username, String password,
                            String lastWillTopic, int lastWillQos, String lastWillMessage,
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

    public MqttClientEntity(MqttClient mqttClient) {
        this.clientId = mqttClient.getClientId();
        this.cleanSession = mqttClient.isCleanSession();
        this.username = mqttClient.getUsername();
        this.password = mqttClient.getPassword();
        this.lastWillTopic = mqttClient.getLastWillTopic();
        this.lastWillQos = mqttClient.getLastWillQos();
        this.lastWillMessage = mqttClient.getLastWillMessage();
        this.lastWillRetain = mqttClient.isLastWillRetain();
        this.keepAlive = mqttClient.getKeepAlive();
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

    public int getLastWillQos() {
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public boolean isActive() {
        return active;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MqttClientEntity{" +
                "id=" + id +
                ", clientId='" + clientId + '\'' +
                ", cleanSession=" + cleanSession +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastWillTopic='" + lastWillTopic + '\'' +
                ", lastWillQos='" + lastWillQos + '\'' +
                ", lastWillMessage='" + lastWillMessage + '\'' +
                ", lastWillRetain=" + lastWillRetain +
                ", keepAlive=" + keepAlive +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", active=" + active +
                '}';
    }
}
