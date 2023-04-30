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
public class DiedModel {
    private String timeline;
    private String local;

    public void fromMap(Map<String, String> diedCharacter) {
        this.timeline = diedCharacter.get("Timeline");
        this.local = diedCharacter.get("Local");
    }

}
