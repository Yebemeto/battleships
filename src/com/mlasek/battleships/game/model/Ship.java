package com.mlasek.battleships.game.model;

import java.util.Set;

public class Ship {
    private ShipType type;
    private Set<ShipHullPoint> shipHullPoints;

    public Ship(ShipType type, Set<ShipHullPoint> shipHullPoints) {
        this.type = type;
        this.shipHullPoints = shipHullPoints;
    }

    public ShipType getType() {
        return type;
    }

    public Set<ShipHullPoint> getShipHullPoints() {
        return shipHullPoints;
    }

    public int getRemainingHealth(){
        return (int) shipHullPoints.stream().filter(point -> !point.isHit()).count();
    }
}