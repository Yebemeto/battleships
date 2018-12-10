package com.mlasek.battleships.application.model;


import com.mlasek.battleships.game.model.GameState;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GameStatusResponse {
    public GameStatusResponse(GameState gameStatus, int yourScore, int opponentScore) {
        this.gameStatus = gameStatus;
        this.yourScore = yourScore;
        this.opponentScore = opponentScore;
    }

    private GameState gameStatus;
    private int yourScore;
    private int opponentScore;

    public GameState getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameState gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getYourScore() {
        return yourScore;
    }

    public void setYourScore(int yourScore) {
        this.yourScore = yourScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }
}