package com.mlasek.battleships.application.converter;


import com.mlasek.battleships.application.model.ShotResponse;
import com.mlasek.battleships.application.model.ShotResponseHit;
import com.mlasek.battleships.game.model.ShotResult;
import com.mlasek.battleships.game.model.ShotResultType;

public class ShotResponseConverter {
    private ShotResponseConverter(){}

    public static ShotResponse convert(ShotResult input){
        if(ShotResultType.HIT.equals(input.getResultType())){
            return new ShotResponseHit(input.getShipType(),input.isSunken());
        }else{
            return new ShotResponse(ShotResultType.MISS);
        }
    }
}