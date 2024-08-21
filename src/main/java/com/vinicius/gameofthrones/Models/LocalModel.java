package com.vinicius.gameofthrones.Models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "local")
public class LocalModel {
    private String name;
    private String type;
    private String location;
    private String geography;
    private String population;
    private String rulers;
    private String culture;
    private String religion;
    private String capital;
    private String castles;
    private String towns;
    private String placesOfNote;
    private String founded;
}