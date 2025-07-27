package com.vinicius.gameofthrones.Models.Season;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class SeasonPreview extends RepresentationModel<SeasonPreview> {
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
