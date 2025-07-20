package com.vinicius.gameofthrones.Models;

import lombok.Data;

@Data
public class WritersModel {
    private String _id;
    private String name;

    public WritersModel(String name) {
        this.name = name;
    }
}
