package com.vinicius.gameofthrones;

import com.vinicius.gameofthrones.Models.Episode.EpisodeModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GameOfThronesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GameOfThronesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String URL = "https://gameofthrones.fandom.com/wiki/Category:Episodes_of_Game_of_Thrones";

        Document doc = Jsoup.connect(URL).get();
        Elements asides = doc.select("div.category-page__members ul a");
        System.out.println(asides.html());
//        var title = asides.select("h2.pi-item.pi-item-spacing.pi-title.pi-secondary-background").text();
//        System.out.println("========================");
//        asides.forEach(aside -> {
//            aside.select("section.pi-item.pi-group.pi-border-color").forEach(section -> {
//                section.select("section.pi-item.pi-group.pi-border-color").forEach(dv -> {
//
//                    dv.select("div.pi-item.pi-data.pi-item-spacing.pi-border-color").forEach(dataValue -> {
//                        var data = dataValue.attr("data-source");
//                        var value = dataValue.select("div.pi-data-value.pi-font").text();
//                    });
//                });
//            });
//        });
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        Elements rows = doc.select("table");
//        if (!rows.isEmpty()) {
//            var table = rows.get(0);
//            System.out.println(table);
//        }
    }
}
