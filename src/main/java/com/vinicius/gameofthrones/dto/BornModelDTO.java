package com.vinicius.gameofthrones.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BornModelDTO {
    private String timeline;
    private String local;
    
    public void fromMap(Map<String, String> bornCharacter) {
        this.timeline = bornCharacter.get("Timeline");
        this.local = bornCharacter.get("Local");
    }
}
