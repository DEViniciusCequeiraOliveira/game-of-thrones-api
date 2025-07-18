package com.vinicius.gameofthrones.Util;

import lombok.Data;

@Data
public class ProducersModel {
    private String _id;
    private String name;

    public ProducersModel(String name) {
        this.name = name;
    }
}
