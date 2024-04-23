package com.vinicius.gameofthrones.dto;

import java.util.Map;

import com.vinicius.gameofthrones.Models.BornModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BornModelDTO {
    private String timeline;
    private String local;

    public BornModelDTO(BornModel born) {
        this.timeline = born.getTimeline();
        this.local = born.getLocal();
    }

    public void fromMap(Map<String, String> bornCharacter) {
        this.timeline = bornCharacter.get("Timeline");
        this.local = bornCharacter.get("Local");
    }
}
