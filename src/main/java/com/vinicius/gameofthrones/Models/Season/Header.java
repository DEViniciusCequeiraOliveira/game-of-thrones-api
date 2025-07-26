package com.vinicius.gameofthrones.Models.Season;

import lombok.Getter;

@Getter
public enum Header {
    EPISODE("Episode"),
    IMAGE("Image"),
    TITLE("Title"),
    AIR_DATE("Air Date");

    private final String label;

    Header(String label) {
        this.label = label;
    }
}
