package com.vinicius.gameofthrones.Models.Season;

public record SeasonPreview(
        String _id,
        String season,
        String name,
        String href){

    public SeasonPreview(String season, String name, String href) {
        this("", season, name, href);
    }
}
