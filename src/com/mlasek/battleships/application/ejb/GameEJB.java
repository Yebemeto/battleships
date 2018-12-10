package com.mlasek.battleships.application.ejb;

import com.mlasek.battleships.game.model.*;

import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Singleton
public class GameEJB implements GameEJBLocal {

    private Map<UUID, AuthorizedGame> games = new HashMap<UUID, AuthorizedGame>();
    @Override
    public AuthorizedGame getGame(UUID uuid) {
        return games.get(uuid);
    }

    @Override
    public void addGame(UUID uuid) {
        AuthorizedGame game = new AuthorizedGame();
        games.put(uuid, game);
    }

    @Override
    public UUID setupGame(AuthorizedGame game) {
        UUID token = UUID.randomUUID();
        game.addPlayer(token,new Player(FixedGameSetup.setupPlayerOneTargetGameBoard(), PlayerNumber.PLAYER_ONE));
        return token;
    }

    @Override
    public UUID joinGame(AuthorizedGame game) {
        UUID token = UUID.randomUUID();
        Player playerTwo = new Player(FixedGameSetup.setupPlayerTwoTargetGameBoard(), PlayerNumber.PLAYER_TWO);
        playerTwo.setState(GameState.YOUR_TURN);
        game.addPlayer(token,playerTwo);
        game.getPlayers().get(PlayerNumber.PLAYER_ONE).setState(GameState.WAITING_FOR_OPPONENT_MOVE);
        return token;
    }

    @Override
    public ShotResult shoot(AuthorizedGame game, UUID playerToken, Coordinates shotCoordinates) {
        Player thisPlayer = game.getPlayerByToken(playerToken);
        Player otherPlayer = game.getPlayers().get(thisPlayer.getNumber().other());
        ShotResult shotResult = verifyShot(thisPlayer.getTargetBoard(),shotCoordinates);
        if(ShotResultType.HIT.equals(shotResult.getResultType())){
            thisPlayer.addPoint();
            if(thisPlayer.hasWon()){
                thisPlayer.setState(GameState.YOU_WON);
                otherPlayer.setState(GameState.YOU_LOST);
            }
            return shotResult;
        }else{
            thisPlayer.setState(GameState.WAITING_FOR_OPPONENT_MOVE);
            otherPlayer.setState(GameState.YOUR_TURN);
            return shotResult;
        }
    }

    @Override
    public boolean isPlayerTurn(AuthorizedGame game, UUID playerToken) {
        Player thisPlayer = game.getPlayerByToken(playerToken);
        return GameState.YOUR_TURN.equals(thisPlayer.getState());
    }


    private ShotResult verifyShot(final GameBoard target, final Coordinates shotCoordinate){

        final AtomicReference<Ship> shipReference = new AtomicReference<>();
        Optional<ShipHullPoint> point =target.getShips().stream()
                .flatMap(ship ->{
                    shipReference.set(ship);
                    return ship.getShipHullPoints().stream();
                })
                .filter(hull -> hull.getCoordinates().equals(shotCoordinate))
                .findFirst();
        if(point.isPresent()){
            if(point.get().isHit()){
                return ShotResult.createAlreadyHitResult();
            }
            point.get().setHit(true);
            int remainingHealth = shipReference.get().getRemainingHealth();
            boolean sunken = (remainingHealth==0);
            return new ShotResult(ShotResultType.HIT, sunken, shipReference.get().getType());
        }else{
            return ShotResult.createMissResult();
        }

    }
}