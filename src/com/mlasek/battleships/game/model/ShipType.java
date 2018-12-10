package com.mlasek.battleships.game.model;

public enum ShipType {

    ONE_DECKER(1), TWO_DECKER(2), THREE_DECKER(3), FOUR_DECKER(4);

    ShipType(int size) {
        this.size = size;
    }

    private final int size;

    public int getSize() {
        return size;
    }
}