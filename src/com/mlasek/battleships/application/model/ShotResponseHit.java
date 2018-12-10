package com.mlasek.battleships.application.model;


import com.mlasek.battleships.game.model.ShipType;
import com.mlasek.battleships.game.model.ShotResultType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShotResponseHit extends ShotResponse {
    public ShotResponseHit(ShipType shipType, boolean sunken) {
        super(ShotResultType.HIT);
        this.shipType = shipType;
        this.sunken = sunken;
    }

    private ShipType shipType;
    private boolean sunken;

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public boolean isSunken() {
        return sunken;
    }

    public void setSunken(boolean sunken) {
        this.sunken = sunken;
    }
}