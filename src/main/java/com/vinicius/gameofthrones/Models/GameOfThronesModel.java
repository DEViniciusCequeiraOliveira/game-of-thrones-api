package com.vinicius.gameofthrones.Models;

import com.vinicius.gameofthrones.Models.Season.SeasonModel;
import com.vinicius.gameofthrones.Models.Season.SeasonPreview;
import com.vinicius.gameofthrones.Util.CreatorModel;
import com.vinicius.gameofthrones.Util.ProducersModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "gameofthrones")
public class GameOfThronesModel extends RepresentationModel<GameOfThronesModel> {
    @Id
    private String _id;

    private String image;
    private String name;
    private String format;
    private List<SeasonPreview> season;
    private String episodes;
    private String premiere;
    private String finale;
    private String network;
    private String premiereDate;
    private String finaleDate;
    private String runtime;
    private List<StarringModel> starring;
    private List<CreatorModel> creators;
    private List<ProducersModel> producers;
    private List<WritersModel> screenplay;
    private List<DirectorModel> directors;

    public GameOfThronesModel(Map<String, Object> dados) {
        this.image = (String) dados.get("image");
        this.name = "Game of Thrones";
        this.format = (String) dados.get("Format");
        this.season = (List<SeasonPreview>) dados.get("Seasons");
        this.episodes = (String) dados.get("Episodes");
        this.premiere = (String) dados.get("Premiere");
        this.finale = (String) dados.get("Finale");
        this.network = (String) dados.get("Network");
        this.premiereDate = (String) dados.get("First");
        this.finaleDate = (String) dados.get("Final");
        this.runtime = (String) dados.get("Runtime");
        this.starring = (List<StarringModel>) dados.get("Starring");
        this.creators = (List<CreatorModel>) dados.get("Creator");
        this.producers = (List<ProducersModel>) dados.get("Producers");
        this.screenplay = (List<WritersModel>) dados.get("Writers");
        this.directors = (List<DirectorModel>) dados.get("Directors");
    }
}
