package io.jyotirmay.digitaltwin.cloudapi.v1.model;

import java.io.Serializable;

public class ResponseModel implements Serializable {

    private int status;

    private String message;

    private Object response;

    public ResponseModel(int status, String message, Object response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getResponse() {
        return response;
    }
}
