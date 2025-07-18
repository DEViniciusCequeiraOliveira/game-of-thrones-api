package com.vinicius.gameofthrones.Util;

import com.vinicius.gameofthrones.Models.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.vinicius.gameofthrones.Util.ScrapingUtil.urlGameOfThrones;

public class Main {
    public static void main(String[] args) throws IOException {
        GameOfThrones();
    }

    public static String GameOfThrones() throws IOException {
        var model = new GameOfThronesModel();
        makeLIs makeList = new makeLIs();
        Map<String, Object> modelMap = new HashMap<>();
        List<SeasonModel> seasonModel = new ArrayList<>();
        List<StarringModel> starring = new ArrayList<>();
        List<CreatorModel> creators = new ArrayList<>();
        List<ProducersModel> producers = new ArrayList<>();
        List<ScreenplayModel> screenplay = new ArrayList<>();
        List<DirectorModel> directors = new ArrayList<>();
        var elemento = "div.pi-item.pi-data.pi-item-spacing.pi-border-color";

        Document doc = Jsoup.connect(urlGameOfThrones).get();
        Elements elements = doc.select("aside.portable-infobox.pi-background.pi-border-color.pi-theme-Westeros.pi-theme-Thrones.pi-layout-default.type-episode");

        elements.forEach(element -> {
            var sesson = element.select("section.pi-item.pi-group.pi-border-color");
            sesson.forEach(session -> {
                var dataSource = session.select(elemento);
                dataSource.forEach(dataValue -> {
                    var data = dataValue.attr("data-source");

                    if (data.equals("Seasons")) {
                        var seasonList = makeListSeason(seasonModel, dataValue);
                        modelMap.put(data, seasonList);
                        System.out.println("=================");
                        System.out.println(seasonModel);
                        System.out.println("=================");
                    } else if (data.equals("Starring")) {
                        var starringList = makeList.makeList(starring, dataValue, StarringModel::new);
                        modelMap.put(data, starringList);
                    } else if (data.equals("Creators")) {
                        makeList.makeList(creators, dataValue, CreatorModel::new);
                        System.out.println("=================");
                        System.out.println(creators);
                        System.out.println("=================");
                    } else if (data.equals("Producers")) {
                        makeList.makeList(producers, dataValue, ProducersModel::new);
                        System.out.println("=================");
                        System.out.println(producers);
                        System.out.println("=================");
                    } else if (data.equals("Screenplay")) {
                        makeList.makeList(screenplay, dataValue, ScreenplayModel::new);
                        System.out.println("=================");
                        System.out.println(screenplay);
                        System.out.println("=================");
                    } else if (data.equals("Directors")) {
                        makeList.makeList(directors, dataValue, DirectorModel::new);
                        System.out.println("=================");
                        System.out.println(directors);
                        System.out.println("=================");
                    } else {
                        var label = dataValue.select("h3.pi-data-label.pi-secondary-font").text();
                        var value = dataValue.select("div.pi-data-value.pi-font").text();
                        System.out.println(label);
                        System.out.println(value);
                    }
                });
            });

        });


        return elemento.toString();
    }

    public static List<SeasonModel> makeListSeason(List<SeasonModel> dados, Element dataValue) {
        var value = dataValue.select("div.pi-data-value.pi-font").select("a");
        value.forEach(seasonValue -> {
            dados.add(new SeasonModel(seasonValue.text(), seasonValue.attr("title"), seasonValue.attr("href")));
        });
        return dados;
    }
}

class makeLIs implements IGameOfThrones {

    @Override
    public <T> List<T> makeList(List<T> dados, Element dataValue, Function<String, T> creator) {
        var value = dataValue.select("div.pi-data-value.pi-font").select("a");
        value.forEach(seasonValue -> {
            dados.add(creator.apply(seasonValue.text()));
        });
        return dados;
    }
}
