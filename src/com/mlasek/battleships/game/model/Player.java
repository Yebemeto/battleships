package com.mlasek.battleships.game.model;

public class Player {
    public Player(GameBoard targetBoard, PlayerNumber number) {
        this.targetBoard = targetBoard;
        this.number = number;
    }

    //This is the board which this player shoots at
    //so it is in fact the other player's board
    private GameBoard targetBoard;
    private int score = 0;
    private GameState state = GameState.AWAITING_PLAYERS;
    private PlayerNumber number;

    public int getScore() {
        return score;
    }

    public void addPoint(){
        score++;
    }

    public boolean hasWon(){
        return !targetBoard.getShips().stream().anyMatch(ship -> ship.getShipHullPoints().stream().anyMatch(shipHullPoint -> !shipHullPoint.isHit()));
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameBoard getTargetBoard() {
        return targetBoard;
    }

    public PlayerNumber getNumber() {
        return number;
    }
}