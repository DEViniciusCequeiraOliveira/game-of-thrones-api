package com.vinicius.gameofthrones.Models.Episode;

import com.vinicius.gameofthrones.Models.DirectorModel;
import com.vinicius.gameofthrones.Models.StarringModel;
import com.vinicius.gameofthrones.Models.WritersModel;
import com.vinicius.gameofthrones.Util.ProducersModel;

import java.util.List;

public class EpisodeModel {

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
    private List<ProducersModel> producers;
    private List<WritersModel> screenplay;
    private List<DirectorModel> directors;
}
