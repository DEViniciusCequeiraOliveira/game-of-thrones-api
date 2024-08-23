package com.vinicius.gameofthrones;

import com.vinicius.gameofthrones.Models.CastlesDados;
import com.vinicius.gameofthrones.Models.CastlesModel;
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
        final String urlCastles = "https://gameofthrones.fandom.com/wiki/Category:Castles";
        final String url = "https://gameofthrones.fandom.com";

        List<String> castles = new ArrayList<>();
        Document doc = Jsoup.connect(urlCastles).get();
        Elements links = doc.select(".category-page__member-link");
        Map<String, Object> castlesMap = new HashMap<>();
        Map<String, Object> castlesObj = new HashMap<>();

        links.forEach(link -> {
            try {
                Document docChar = Jsoup.connect(url + link.attr("href")).get();
                var categoryCastlesName = link.text();
                castlesMap.put("nome", categoryCastlesName);
                Elements links2 = docChar.select(".category-page__member-link");
                links2.forEach(element -> {
                    try {
                        Document docChar2 = Jsoup.connect(url + links2.attr("href")).get();
                        List<CastlesModel> castlesModels = new ArrayList<>();
//Desgraçaaaaaaa
                        element.forEach(castle -> {
                            String label = docChar2.select("h3.pi-data-label").text();
                            String value = docChar2.select("div.pi-data-value.pi-font").text();
                            CastlesModel castlesModel = new CastlesModel();
                            castlesObj.put(label, value);
                            castlesModel.fromMap(castlesObj);
                            castlesModels.add(castlesModel);
                        });
                        CastlesDados castlesDados = new CastlesDados();
                        castlesMap.put("castles", castlesModels);
                        castlesDados.fromMap(castlesMap);
                        System.out.println(castlesDados);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            } catch (Exception e) {
                return;
            }

        });
    }
}
