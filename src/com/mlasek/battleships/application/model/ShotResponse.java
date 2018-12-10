package com.mlasek.battleships.application.model;



import com.mlasek.battleships.game.model.ShotResultType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShotResponse {
    public ShotResponse(ShotResultType result) {
        this.result = result;
    }

    private ShotResultType result;

    public ShotResultType getResult() {
        return result;
    }

    public void setResult(ShotResultType result) {
        this.result = result;
    }
}