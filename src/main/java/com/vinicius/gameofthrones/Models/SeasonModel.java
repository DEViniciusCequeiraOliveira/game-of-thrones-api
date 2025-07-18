package com.vinicius.gameofthrones.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonModel {
    private String _id;
    private String season;
    private String name;
    private String href;

    public SeasonModel(String season, String title, String href) {
        this.season = season;
        this.name = title;
        this.href = href;
    }
}
