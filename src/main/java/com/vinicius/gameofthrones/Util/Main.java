package com.vinicius.gameofthrones.Util;

import com.vinicius.gameofthrones.Models.Episode.EpisodeModel;
import com.vinicius.gameofthrones.Models.staff.DirectorModel;
import com.vinicius.gameofthrones.Models.staff.Staff;
import com.vinicius.gameofthrones.Models.staff.WritersModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<String, Object> map = new HashMap<>();

        String base = "https://gameofthrones.fandom.com";
        String URL = "https://gameofthrones.fandom.com/wiki/Category:Episodes_of_Game_of_Thrones";

        Document doc = Jsoup.connect(URL).get();
        Elements linksCategorias = doc.select("div.category-page__members ul a");
        linksCategorias.forEach(category -> {
            var episodeLinks = category.attr("href");
            try {
                Document doc2 = Jsoup.connect(base + episodeLinks).get();
                Elements links = doc2.select("ul.category-page__trending-pages li a");
                links.forEach(link -> {
                    var linkEpisode = link.attr("href");
                    try {
                        Document doc3 = Jsoup.connect(base + linkEpisode).get();
                        Elements episodes = doc3.select("aside.portable-infobox.pi-background.pi-border-color.pi-theme-Westeros.pi-theme-Thrones.pi-layout-default.type-episode");
                        map.put("episodes", episodes.size());
                        episodes.forEach(episode -> {

                            var title = episode.select("h2 pi-item pi-item-spacing pi-title pi-secondary-background".replace(" ", ".")).text();
                            map.put("titulo", title);
                            var image = episode.select("figure.pi-image-thumbnail a").attr("href");
                            map.put("imagem", image);
                            episode.select("section.pi-item.pi-group.pi-border-color").forEach(section -> {
                                var data = episode.select("div.pi-item.pi-data.pi-item-spacing.pi-border-color");
                                if (data.size() >= 4) {
                                    List<WritersModel> writersModels = new ArrayList<>();
                                    List<DirectorModel> directorModels = new ArrayList<>();

                                    var airDate = data.get(0).select("div.pi-data-value.pi-font").text();
                                    map.put("airDate", airDate);
                                    var runTime = data.get(1).select("div.pi-data-value.pi-font").text();
                                    map.put("runTime", runTime);

                                    data.get(2).select("div.pi-data-value.pi-font").forEach(writtens -> {
                                        writersModels.add(new WritersModel(writtens.select("a title").text()));
                                    });
                                    data.get(3).select("div.pi-data-value.pi-font").forEach(directors -> {
                                        directorModels.add(new DirectorModel(directors.select("a title").text()));
                                    });
                                    map.put("writers", writersModels);
                                    map.put("directors", directorModels);
                                }
                            });

                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            var episode = new EpisodeModel(map);
            System.out.println(episode);
        });
    }
}
