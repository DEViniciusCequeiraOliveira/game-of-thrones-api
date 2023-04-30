package com.vinicius.gameofthrones.Models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BornModel {
    private String timeline;
    private String local;

    public void fromMap(Map<String, String> bornCharacter) {
        System.out.println("--------------------------");
        System.out.println(bornCharacter);
        System.out.println("--------------------------");
        this.timeline = bornCharacter.get("Timeline");
        this.local = bornCharacter.get("Local");
    }
}
