package com.vinicius.gameofthrones.Util;

import lombok.Data;

@Data
public class CreatorModel {
    private String _id;
    private String name;

    public CreatorModel(String name) {
        this.name = name;
    }
}
