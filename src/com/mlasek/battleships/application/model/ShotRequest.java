package com.mlasek.battleships.application.model;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShotRequest {
    @Pattern(message = "Invalid coordinates. " +
            "Valid coordinates: [A-J][1-10], ex. A5, J10",
            regexp = "[A-J]([1-9]{1}|10)$")
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}