package com.vinicius.gameofthrones.Models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameOfThronesModel {
    private String _id;
    private String image;
    private String name;
    private String format;
    private List<String> season;
    private String episodes;
    private String premiere;
    private String finale;
    private String network;
    private String premiereDate;
    private String finaleDate;
    private String runtime;
    private List<String> starring;
    private List<String> creators;
    private List<String> producers;
    private List<String> screenplay;
    private List<String> directors;
}
