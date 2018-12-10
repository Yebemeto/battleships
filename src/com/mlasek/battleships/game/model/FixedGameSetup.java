package com.mlasek.battleships.game.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FixedGameSetup {

    private FixedGameSetup(){}

    public static GameBoard setupPlayerTwoTargetGameBoard() {
        List<Ship> ships = new ArrayList<Ship>();
        Set<ShipHullPoint> shipHull = new HashSet<ShipHullPoint>();

        shipHull.add(new ShipHullPoint(new Coordinates(0,0)));
        ships.add(new Ship(ShipType.ONE_DECKER, shipHull));
        shipHull = new HashSet<ShipHullPoint>();

        shipHull.add(new ShipHullPoint(new Coordinates(0,3)));
        shipHull.add(new ShipHullPoint(new Coordinates(1,3)));
        ships.add(new Ship(ShipType.TWO_DECKER, shipHull));
        shipHull = new HashSet<ShipHullPoint>();

        shipHull.add(new ShipHullPoint(new Coordinates(0,6)));
        shipHull.add(new ShipHullPoint(new Coordinates(1,6)));
        shipHull.add(new ShipHullPoint(new Coordinates(2,6)));
        ships.add(new Ship(ShipType.THREE_DECKER, shipHull));
        shipHull = new HashSet<ShipHullPoint>();

        shipHull.add(new ShipHullPoint(new Coordinates(0,9)));
        shipHull.add(new ShipHullPoint(new Coordinates(1,9)));
        shipHull.add(new ShipHullPoint(new Coordinates(2,9)));
        shipHull.add(new ShipHullPoint(new Coordinates(3,9)));
        ships.add(new Ship(ShipType.FOUR_DECKER, shipHull));

        return new GameBoard(ships);
    }

    public static GameBoard setupPlayerOneTargetGameBoard() {
        List<Ship> ships = new ArrayList<Ship>();
        Set<ShipHullPoint> shipHull = new HashSet<ShipHullPoint>();

        shipHull.add(new ShipHullPoint(new Coordinates(9,9)));
        ships.add(new Ship(ShipType.ONE_DECKER, shipHull));
        shipHull = new HashSet<ShipHullPoint>();

        shipHull.add(new ShipHullPoint(new Coordinates(9,6)));
        shipHull.add(new ShipHullPoint(new Coordinates(8,6)));
        ships.add(new Ship(ShipType.TWO_DECKER, shipHull));
        shipHull = new HashSet<ShipHullPoint>();

        shipHull.add(new ShipHullPoint(new Coordinates(9,3)));
        shipHull.add(new ShipHullPoint(new Coordinates(8,3)));
        shipHull.add(new ShipHullPoint(new Coordinates(7,3)));
        ships.add(new Ship(ShipType.THREE_DECKER, shipHull));
        shipHull = new HashSet<ShipHullPoint>();

        shipHull.add(new ShipHullPoint(new Coordinates(0,0)));
        shipHull.add(new ShipHullPoint(new Coordinates(0,1)));
        shipHull.add(new ShipHullPoint(new Coordinates(0,2)));
        shipHull.add(new ShipHullPoint(new Coordinates(0,3)));
        ships.add(new Ship(ShipType.FOUR_DECKER, shipHull));

        return new GameBoard(ships);
    }


}