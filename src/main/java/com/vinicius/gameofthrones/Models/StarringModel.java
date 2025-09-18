package com.vinicius.gameofthrones.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document
public class StarringModel {
    @Id
    private String _id;
    private String image;
    private String name;
    private String fullName;
    private String born;
    private String locate;
    private String role;

    public StarringModel(String name) {
        this.name = name;
    }

    public StarringModel(String image, String name, String fullName, String born, String locate,Map<String, String> map) {
        this.image = image;
        this.name = name;
        this.fullName = fullName;
        this.born = born;
        this.locate = locate;
        this.role = map.get("Role");
    }


}
