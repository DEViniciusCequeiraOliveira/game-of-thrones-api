//package com.vinicius.gameofthrones.Util;
//
//import com.vinicius.gameofthrones.Models.*;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.regex.Pattern;
//
//import static com.vinicius.gameofthrones.Util.ScrapingUtil.urlGameOfThrones;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        GameOfThrones();
//    }
//
//    public static void GameOfThrones() throws IOException {
//
//        makeLIs makeList = new makeLIs();
//        Map<String, Object> modelMap = new HashMap<>();
//
//        List<SeasonModel> seasonModel = new ArrayList<>();
//        List<StarringModel> starring = new ArrayList<>();
//        List<CreatorModel> creators = new ArrayList<>();
//        List<ProducersModel> producers = new ArrayList<>();
//        List<WritersModel> writers = new ArrayList<>();
//        List<DirectorModel> directors = new ArrayList<>();
//
//        var elemento = "div.pi-item.pi-data.pi-item-spacing.pi-border-color";
//
//        Document doc = Jsoup.connect(urlGameOfThrones).get();
//        Elements elements = doc.select("aside.portable-infobox.pi-background.pi-border-color.pi-theme-Westeros.pi-theme-Thrones.pi-layout-default.type-episode");
//
//        elements.forEach(element -> {
//            var sesson = element.select("section.pi-item.pi-group.pi-border-color");
//            sesson.forEach(session -> {
//                var dataSource = session.select(elemento);
//                dataSource.forEach(dataValue -> {
//                    var data = dataValue.attr("data-source");
//                    System.out.println(data);
//                    if (data.equals("Seasons")) {
//                        var seasonList = makeListSeason(seasonModel, dataValue);
//                        modelMap.put(data, seasonList);
//                    } else if (data.equals("Starring")) {
//                        var starringList = makeList.makeList(starring, dataValue, StarringModel::new);
//                        modelMap.put(data, starringList);
//                    } else if (data.equals("Creator")) {
//                        makeList.makeList(creators, dataValue, CreatorModel::new);
//                        modelMap.put(data, creators);
//                    } else if (data.equals("Producers")) {
//                        makeList.makeList(producers, dataValue, ProducersModel::new);
//                        modelMap.put(data, producers);
//                    } else if (data.equals("Writers")) {
//                        makeList.makeList(writers, dataValue, WritersModel::new);
//                        modelMap.put(data, writers);
//                    } else if (data.equals("Directors")) {
//                        makeList.makeList(directors, dataValue, DirectorModel::new);
//                        modelMap.put(data, directors);
//                    } else {
//                        var label = dataValue.select("h3.pi-data-label.pi-secondary-font").text().replace(" ", "").replace("(s)", "");
//                        var value = dataValue.select("div.pi-data-value.pi-font").text().replaceAll("\\[\\d+\\]", "");
//                        modelMap.put(data, value);
//                    }
//                });
//            });
//        });
//        var model = new GameOfThronesModel(modelMap);
//        System.out.println(model);
//    }
//
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
//}
