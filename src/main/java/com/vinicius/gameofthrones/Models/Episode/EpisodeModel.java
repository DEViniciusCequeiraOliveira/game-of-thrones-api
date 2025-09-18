package com.vinicius.gameofthrones.Models.Episode;

import com.vinicius.gameofthrones.Models.staff.DirectorModel;
import com.vinicius.gameofthrones.Models.StarringModel;

import java.util.List;

public class EpisodeModel {
    private String _id;
    private String season;
    private String episodes;
    private String premiere;
    private String finale;
    private String network;
    private String premiereDate;
    private String finaleDate;
    private String runtime;
    private String image;
    private String name;
    private String homeVideo;
    private List<StarringModel> starring;
    private List<DirectorModel> directors;
}
