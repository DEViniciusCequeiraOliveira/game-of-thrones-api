package com.vinicius.gameofthrones.dto;

import com.vinicius.gameofthrones.Models.house.HouseModel;
import com.vinicius.gameofthrones.Models.house.HouseRelation;

import java.util.List;

public record HouseDTO(
    String _id,
    String name,
    String coatOfArms,
    String words,
    List<HouseRelation> titles,
    String otherEstates,
    String seat,
    String region,
    String head,
    String heir,
    String overlords,
    List<HouseRelation> vassals,
    String religion,
    String founded){
    public HouseDTO(HouseModel dados) {
        this(dados.get_id(),
                dados.getName(),
                dados.getCoatOfArms(),
                dados.getWords(),
                dados.getTitles(),
                dados.getOtherEstates(),
                dados.getRegion(),
                dados.getSeat(),
                dados.getHead(),
                dados.getHeir(),
                dados.getOverlords(),
                dados.getVassals(),
                dados.getReligion(),
                dados.getFounded());
    }
}
