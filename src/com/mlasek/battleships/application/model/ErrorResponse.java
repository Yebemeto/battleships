package com.mlasek.battleships.application.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorResponse {
    public ErrorResponse(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}