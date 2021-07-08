package io.jyotirmay.digitaltwin.cloudapi.v1.util;

public final class ApiConstant {

    private ApiConstant(){
        throw new IllegalStateException();
    }

    public static final String EXISTS = "exists";
    public static final String MQTT_CLIENT = "mqtt_client";
    public static final String ID = "id";
    public static final String CLEAN_SESSION = "clean_session";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String LAST_WILL_TOPIC = "last_will_topic";
    public static final String LAST_WILL_QOS = "last_will_qos";
    public static final String LAST_WILL_MESSAGE = "last_will_message";
    public static final String LAST_WILL_RETAIN = "last_will_retain";
    public static final String KEEP_ALIVE = "keep_alive";
    public static final String CLIENTID = "clientId";
    public static final String CLIENT_ID = "client_id";
}
