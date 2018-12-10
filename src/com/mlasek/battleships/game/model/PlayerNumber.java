package com.mlasek.battleships.game.model;

public enum PlayerNumber {
   PLAYER_ONE, PLAYER_TWO;

    public PlayerNumber other(){
        if(this.equals(PLAYER_ONE)){
            return PLAYER_TWO;
        }else{
            return PLAYER_ONE;
        }
    }
}