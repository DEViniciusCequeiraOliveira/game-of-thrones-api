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
    String uid;
    String name;
    String born;
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

    public void fromMap(Map<String, String> datasCharacter) {
        this.name = datasCharacter.get("Name");
        this.born = datasCharacter.get("Born");
        this.died = datasCharacter.get("Died");
        this.allegiance = datasCharacter.get("Allegiance");
        this.title = datasCharacter.get("Title(s)");
        this.culture = datasCharacter.get("Culture");
        this.father = datasCharacter.get("Father");
        this.mother = datasCharacter.get("Mother");
        this.sibling = datasCharacter.get("Sibling(s)");
        this.series = datasCharacter.get("Series");
        this.season = datasCharacter.get("Season(s)");
        this.appeared = datasCharacter.get("Appeared in");
        this.firstSee = datasCharacter.get("First seen in");
        this.lastSee = datasCharacter.get("Last seen in");
        this.diedIn = datasCharacter.get("Died in");
        this.mentioned = datasCharacter.get("Mentioned in");
        this.portrayed = datasCharacter.get("Portrayed by");
    }
}
