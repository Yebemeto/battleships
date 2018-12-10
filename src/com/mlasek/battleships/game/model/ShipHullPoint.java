package com.mlasek.battleships.game.model;

public class ShipHullPoint {


    public ShipHullPoint(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    private Coordinates coordinates;
    private boolean hit=false;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipHullPoint that = (ShipHullPoint) o;

        return coordinates.equals(that.coordinates);

    }

    @Override
    public int hashCode() {
        return coordinates.hashCode();
    }
}