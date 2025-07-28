package com.vinicius.gameofthrones.Models.Season;

import lombok.Data;


@Data
public class SeasonPreview {
    private String _id;
    private String season;
    private String name;
    private String href;

    public SeasonPreview(String season, String name, String href) {
        this.season = season;
        this.name = name;
        this.href = href;
    }
}
