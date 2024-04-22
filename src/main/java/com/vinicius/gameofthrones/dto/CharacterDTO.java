package com.vinicius.gameofthrones.dto;

import com.vinicius.gameofthrones.Models.BornModel;
import com.vinicius.gameofthrones.Models.CharacterModel;
import com.vinicius.gameofthrones.Models.DiedModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {
    String _id;
    String name;
    BornModel born;
    DiedModel died;
    String allegiance;
    String title;
    String culture;
    String father;
    String mother;
    String sibling;
    String series;
    String season;
    String appeared;
    String firstSee;
    String lastSee;
    String diedIn;
    String mentioned;
    String portrayed;
    
    public CharacterDTO(CharacterModel character) {
        this._id = character.get_id();
        this.name = character.getName();
        this.born = character.getBorn();
        this.died = character.getDied();
        this.allegiance = character.getAllegiance();
        this.title = character.getTitle();
        this.culture = character.getCulture();
        this.father = character.getFather();
        this.mother = character.getMother();
        this.sibling = character.getSibling();
        this.series = character.getSeries();
        this.season = character.getSeason();
        this.appeared = character.getAppeared();
        this.firstSee = character.getFirstSee();
        this.lastSee = character.getLastSee();
        this.diedIn = character.getDiedIn();
        this.mentioned = character.getMentioned();
        this.portrayed = character.getPortrayed();  
    }
}
