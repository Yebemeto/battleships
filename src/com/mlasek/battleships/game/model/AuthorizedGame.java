package com.mlasek.battleships.game.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthorizedGame extends Game {
    private Map<UUID, PlayerNumber> tokens = new HashMap<UUID, PlayerNumber>(PLAYER_COUNT);

    public Map<UUID, PlayerNumber> getTokens() {
        return tokens;
    }

    public void addPlayer(UUID playerToken, Player player){
        PlayerNumber number = player.getNumber();
        getPlayers().put(number,player);
        getTokens().put(playerToken,number);
    }

    public Player getPlayerByToken(UUID playerToken){
        PlayerNumber number = getTokens().get(playerToken);
        return getPlayers().get(number);
    }
}