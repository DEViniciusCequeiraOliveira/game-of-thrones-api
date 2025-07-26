package com.vinicius.gameofthrones.Models.Season;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Document
public class SeasonModel {
    @Id
    private String _id;

    private String image;
    private List<String> episodes;
    private String premiere;
    private String finale;
    private String runtime;
    private String starring;
    private String producers;
    private String writers;
    private String directors;
    private String cronologia;
}
