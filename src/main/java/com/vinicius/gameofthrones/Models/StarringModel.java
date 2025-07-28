package com.vinicius.gameofthrones.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarringModel {
    private String _id;
    private String image;
    private String name;
    private String fullName;
    private String born;
    private String locate;

    public StarringModel(String name) {
        this.name = name;
    }

    public StarringModel(String image, String name, String fullName, String born, String locate) {
        this.image = image;
        this.name = name;
        this.fullName = fullName;
        this.born = born;
        this.locate = locate;
    }
}
