package com.vinicius.gameofthrones.Models;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class CharacterModel {
    
    @Id
    String _id;
    String name;
    BornModel born;
    String died;
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
        this.name = (String) datasCharacter.get("Name");
        this.born = (BornModel) datasCharacter.get("Born");
        this.died = (String) datasCharacter.get("Died");
        this.allegiance = (String) datasCharacter.get("Allegiance");
        this.title = (String) datasCharacter.get("Title(s)");
        this.culture = (String) datasCharacter.get("Culture");
        this.father = (String) datasCharacter.get("Father");
        this.mother = (String) datasCharacter.get("Mother");
        this.sibling = (String) datasCharacter.get("Sibling(s)");
        this.series = (String) datasCharacter.get("Series");
        this.season = (String) datasCharacter.get("Season(s)");
        this.appeared = (String) datasCharacter.get("Appeared in");
        this.firstSee = (String) datasCharacter.get("First seen in");
        this.lastSee = (String) datasCharacter.get("Last seen in");
        this.diedIn = (String) datasCharacter.get("Died in");
        this.mentioned = (String) datasCharacter.get("Mentioned in");
        this.portrayed = (String) datasCharacter.get("Portrayed by");
    }
}
