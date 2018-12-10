package com.mlasek.battleships.application.ejb;

import com.mlasek.battleships.game.model.AuthorizedGame;
import com.mlasek.battleships.game.model.Coordinates;
import com.mlasek.battleships.game.model.ShotResult;

import javax.ejb.Local;
import java.util.UUID;

@Local
public interface GameEJBLocal {
    public AuthorizedGame getGame(UUID uuid);
    public void addGame(UUID uuid);
    public UUID setupGame(AuthorizedGame game);
    public UUID joinGame(AuthorizedGame game);
    public ShotResult shoot(AuthorizedGame game, UUID playerToken, Coordinates shotCoordinates);
    public boolean isPlayerTurn(AuthorizedGame game, UUID playerToken);
}