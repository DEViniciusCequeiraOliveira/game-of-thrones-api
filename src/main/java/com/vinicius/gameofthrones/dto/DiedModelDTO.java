package com.vinicius.gameofthrones.dto;

import java.util.Map;

import com.vinicius.gameofthrones.Models.DiedModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiedModelDTO {
    private String timeline;
    private String local;

    public DiedModelDTO(DiedModel died) {
        this.timeline = died.getTimeline();
        this.local = died.getLocal();
    }

    public void fromMap(Map<String, String> diedCharacter) {
    }

}
