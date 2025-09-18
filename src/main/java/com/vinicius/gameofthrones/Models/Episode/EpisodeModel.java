package com.vinicius.gameofthrones.Models.Episode;

import com.vinicius.gameofthrones.Models.staff.DirectorModel;
import com.vinicius.gameofthrones.Models.StarringModel;
import com.vinicius.gameofthrones.Models.staff.WritersModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "episodes")
public class EpisodeModel {
    @Id
    private String _id;
    private String season;
    private String episodes;
    private String runTime;
    private String image;
    private String name;
    private String airDate;

    private List<WritersModel> writers;
    private List<DirectorModel> directors;

    public EpisodeModel(HashMap<String, Object> map) {
        this.name = (String) map.get("titulo");
        this.image = (String) map.get("imagem");
        this.airDate = (String) map.get("airDate");
        this.runTime = (String) map.get("runtime");
        this.directors = (List<DirectorModel>) map.get("directors");
        this.writers = (List<WritersModel>) map.get("writers");
        this.episodes = String.valueOf(map.get("episodes"));
    }
}
