package com.vinicius.gameofthrones.Models;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vinicius.gameofthrones.Util.ScrapingUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "character")
public class CharacterModel {

    @Id
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

    public void fromMap(Map<String, Object> datasCharacter) {
        System.out.println(datasCharacter.get("Born"));

        this.name = ScrapingUtil.removeAscString((String) datasCharacter.get("Name"));
        this.born = (BornModel) datasCharacter.get("Born");
        this.died = (DiedModel) datasCharacter.get("Died");
        this.allegiance = ScrapingUtil.removeAscString((String) datasCharacter.get("Allegiance"));
        this.title = ScrapingUtil.removeAscString((String) datasCharacter.get("Title(s)"));
        this.culture = ScrapingUtil.removeAscString((String) datasCharacter.get("Culture"));
        this.father = ScrapingUtil.removeAscString((String) datasCharacter.get("Father"));
        this.mother = ScrapingUtil.removeAscString((String) datasCharacter.get("Mother"));
        this.sibling = ScrapingUtil.removeAscString((String) datasCharacter.get("Sibling(s)"));
        this.series = ScrapingUtil.removeAscString((String) datasCharacter.get("Series"));
        this.season = ScrapingUtil.removeAscString((String) datasCharacter.get("Season(s)"));
        this.appeared = ScrapingUtil.removeAscString((String) datasCharacter.get("Appeared in"));
        this.firstSee = ScrapingUtil.removeAscString((String) datasCharacter.get("First seen in"));
        this.lastSee = ScrapingUtil.removeAscString((String) datasCharacter.get("Last seen in"));
        this.diedIn = ScrapingUtil.removeAscString((String) datasCharacter.get("Died in"));
        this.mentioned = ScrapingUtil.removeAscString((String) datasCharacter.get("Mentioned in"));
        this.portrayed = ScrapingUtil.removeAscString((String) datasCharacter.get("Portrayed by"));
    }

}
