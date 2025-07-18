package com.vinicius.gameofthrones.Models;

import lombok.Data;

@Data
public class DirectorModel {
    private String _id;
    private String name;

    public DirectorModel(String name) {
        this.name = name;
    }
}
