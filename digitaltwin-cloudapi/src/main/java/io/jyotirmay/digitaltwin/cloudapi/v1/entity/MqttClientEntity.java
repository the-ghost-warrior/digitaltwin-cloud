package io.jyotirmay.digitaltwin.cloudapi.v1.entity;

import io.jyotirmay.digitaltwin.cloudapi.v1.model.MqttClient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity(name = "mqtt_client")
public class MqttClientEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;

    private boolean cleanSession;

    private String username;

    private String password;

    private String lastWillTopic;

    private int lastWillQos;

    private String lastWillMessage;

    private boolean lastWillRetain;

    private int keepAlive;

    private Date createdOn;

    private Date updatedOn;

    private boolean active;

    public MqttClientEntity(){

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
                ", lastWillQos=" + lastWillQos +
                ", lastWillMessage='" + lastWillMessage + '\'' +
                ", lastWillRetain=" + lastWillRetain +
                ", keepAlive=" + keepAlive +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", active=" + active +
                '}';
    }
}
