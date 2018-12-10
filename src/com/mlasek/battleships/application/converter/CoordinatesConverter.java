package com.mlasek.battleships.application.converter;


import com.mlasek.battleships.application.model.ShotRequest;
import com.mlasek.battleships.game.model.Coordinates;

//Converts user-friendly coordinate in form of [A-J][1-10]
// to computer-friendly digits [0-9][0-9]
public class CoordinatesConverter {
    private CoordinatesConverter(){}
    private static final char A_CHAR = 'A';
    private static final int Y_COORDINATE_START_INDEX = 1;

    public static Coordinates convert(ShotRequest input){
        String position = input.getPosition();
        char yChar = position.charAt(0);
        //'A' char ASCII value is 65, and other capital letters follow,
        //so substracting 'A' value from other chars converts
        //letters A-J to numbers 0-9
        int y = yChar-A_CHAR;
        int x = Integer.valueOf(position.substring(Y_COORDINATE_START_INDEX))-1;
        return new Coordinates(x,y);

    }
}