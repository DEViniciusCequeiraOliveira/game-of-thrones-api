package com.vinicius.gameofthrones.Models;

import lombok.Data;

@Data
public class ScreenplayModel {
    private String _id;
    private String name;

    public ScreenplayModel(String name) {
        this.name = name;
    }
}
