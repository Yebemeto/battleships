package com.mlasek.battleships.application.validation;

public class ValidationResources {
    private ValidationResources(){}
    public static final String WRONG_GAME_ID = "Wrong game ID.";
    public static final String NOT_YOUR_TURN = "It is not your turn or game is already finished. Please check game status.";
    public static final String ALREADY_HIT = "You have already hit this coordinate. You have lost your turn. Please remember what you have already hit!";
    public static final String NO_AUTH = "No authorization in header. Please put your token in header 'Auth-Token'.";
    public static final String WRONG_AUTH = "Wrong authorization for this game. Please check your token in header 'Auth-Token'.";
}