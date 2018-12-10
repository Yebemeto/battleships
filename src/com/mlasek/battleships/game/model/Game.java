package com.mlasek.battleships.game.model;

import java.util.HashMap;
import java.util.Map;

public class Game {
    static final int PLAYER_COUNT = 2;

    private Map<PlayerNumber,Player> players = new HashMap<PlayerNumber, Player>(PLAYER_COUNT);

    public Map<PlayerNumber, Player> getPlayers() {
        return players;
    }

}