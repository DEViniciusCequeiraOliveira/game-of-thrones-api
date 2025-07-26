package com.vinicius.gameofthrones.Util;

import com.vinicius.gameofthrones.Models.DirectorModel;
import com.vinicius.gameofthrones.Models.WritersModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException {
        GameOfThrones();
    }

    public static void GameOfThrones() throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        //ModelDataProcessor processor = new ModelDataProcessor(modelMap);
        List<CreatorModel> creators = new ArrayList<>();
        List<ProducersModel> producers = new ArrayList<>();
        List<WritersModel> writers = new ArrayList<>();
        List<DirectorModel> directors = new ArrayList<>();

        var elemento = "div.pi-item.pi-data.pi-item-spacing.pi-border-color";
        var valueTag = "div.pi-data-value.pi-font";

        Document doc = Jsoup.connect("https://gameofthrones.fandom.com/wiki/Game_of_Thrones:_Season_1").get();
        Elements elements = doc.select("aside.portable-infobox.pi-background.pi-border-color.pi-theme-Westeros.pi-theme-Thrones.pi-layout-default.type-episode");
        elements.forEach(element -> {
            var image = element.select("figure.pi-item.pi-image").select("a").attr("href");
            var sections = element.select("div.pi-item.pi-data.pi-item-spacing.pi-border-color");
            sections.forEach(section -> {
                var data = section.select(elemento);
                data.forEach(dataValue -> {
                    //                  System.out.println("+++++++++++============++++++++++");
                    var dataSource = dataValue.attr("data-source");
                    //processor.register(dataSource, dv -> );

                    var value = dataValue.select(valueTag).text();
//                    System.out.println(/*dataSource +" "+*/ value);

                });
            });
        });
        var rows = doc.select("#prettytable");
        rows.forEach(row -> {
            row.select("tr").forEach(cell -> {
                var data = cell.select("td");
//                System.out.println(data);
                if (data.size() >= 4){
                    var episode = data.get(0).text();
                    var image = data.get(1).select("a").attr("href");
                    var title = data.get(2).text();
                    var href = data.get(2).select("a").attr("href");
                    var airDate = data.get(3).text();
                    System.out.println(episode);
                    System.out.println(image);
                    System.out.println(title);
                    System.out.println(href);
                    System.out.println(airDate);
                }
            });

        });
    }

//    public static List<SeasonModel> makeListSeason(List<SeasonModel> dados, Element dataValue) {
//        var value = dataValue.select("div.pi-data-value.pi-font").select("a");
//        Pattern pattern = Pattern.compile("\\[\\d+\\]");
//
//        value.forEach(seasonValue -> {
//            if (pattern.matcher(seasonValue.text()).find()) {
//                return;
//            } else
//                dados.add(new SeasonModel(seasonValue.text(), seasonValue.attr("title"), seasonValue.attr("href")));
//        });
//        return dados;
//    }
}
