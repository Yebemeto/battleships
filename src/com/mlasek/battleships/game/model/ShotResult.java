package com.mlasek.battleships.game.model;

public class ShotResult {
    public ShotResult(ShotResultType resultType, boolean sunken, ShipType shipType) {
        this.resultType = resultType;
        this.sunken = sunken;
        this.shipType = shipType;
    }
    private ShotResult(ShotResultType resultType) {
        this.resultType = resultType;

    }

    private ShotResultType resultType;
    private boolean sunken;
    private ShipType shipType;

    public ShipType getShipType() {
        return shipType;
    }

    public ShotResultType getResultType() {
        return resultType;
    }

    public boolean isSunken() {
        return sunken;
    }

    public static ShotResult createMissResult(){
        return new ShotResult(ShotResultType.MISS);
    }
    public static ShotResult createAlreadyHitResult(){
        return new ShotResult(ShotResultType.ALREADY_HIT);
    }
}