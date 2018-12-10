package com.mlasek.battleships.game.model;

import java.util.List;

public class GameBoard {

    public GameBoard(List<Ship> ships) {
        this.ships = ships;
    }

    private List<Ship> ships;

    public List<Ship> getShips() {
        return ships;
    }

}