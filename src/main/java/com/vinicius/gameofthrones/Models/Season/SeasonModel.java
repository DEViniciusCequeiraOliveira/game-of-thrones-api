package com.vinicius.gameofthrones.Models.Season;

import com.vinicius.gameofthrones.Models.staff.DirectorModel;
import com.vinicius.gameofthrones.Models.Episode.EpisodePreview;
import com.vinicius.gameofthrones.Models.StarringModel;
import com.vinicius.gameofthrones.Models.staff.WritersModel;
import com.vinicius.gameofthrones.Models.staff.ProducersModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Document
public class SeasonModel {
    @Id
    private String _id;

    private String season;
    private String image;
    private String episodesNum;
    private List<EpisodePreview> episodes;
    private String premiere;
    private String finale;
    private String runtime;
    private List<StarringModel> starring;
    private List<ProducersModel> producers;
    private List<WritersModel> writers;
    private List<DirectorModel> directors;
    private String cronologia;

    public SeasonModel(Map<String, Object> dados) {
        this.image = (String) dados.get("Image");
        this.season = (String) dados.get("Title");
        this.episodesNum = (String) dados.get("Episodes");
        this.episodes = (List<EpisodePreview>) dados.get("Episode");
        this.premiere = (String) dados.get("Premiere");
        this.finale = (String) dados.get("Finale");
        this.runtime = (String) dados.get("Runtime");
        this.starring = (List<StarringModel>) dados.get("Starring");
        this.producers = (List<ProducersModel>) dados.get("Producers");
        this.writers = (List<WritersModel>) dados.get("Writers");
        this.directors = (List<DirectorModel>) dados.get("Directors");
        this.cronologia = (String) dados.get("Year");
    }
}
