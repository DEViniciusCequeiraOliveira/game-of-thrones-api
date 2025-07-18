package com.vinicius.gameofthrones.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarringModel {
    private String _id;
    private String name;

    public StarringModel(String name) {
        this.name = name;
    }
}
