package com.vinicius.gameofthrones.Util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vinicius.gameofthrones.Models.CharacterModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrapingUtil {

    final static String url = "https://gameofthrones.fandom.com/wiki/Category:Individuals_appearing_in_Game_of_Thrones";
    final static String urlCharacters = "https://gameofthrones.fandom.com/wiki/";

    public static List<CharacterModel> getDados() throws IOException {
        List<CharacterModel> characters = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select(".category-page__member-link");
        links.forEach(character -> {
            try {
                Document docChar = Jsoup.connect(urlCharacters + character.text()).get();
                Elements datas = docChar.select("div .pi-item .pi-data");

                Map<String, String> datasCharacter = new HashMap<>();
                // Map<Elements, Elements> datasCharacter = new HashMap<>();
                CharacterModel characterModel = new CharacterModel();

                datas.forEach(data -> {
                    System.out.println("-----------------------------------");
                    datasCharacter.put(data.select(".pi-data-label").text(), data.select("div .pi-data-value").text());
                    // datasCharacter.put(data.select(".pi-data-label"), data.select("div
                    // .pi-data-value"));

                    // System.out.println(data);
                    System.out.println("-----------------------------------");
                });
                System.out.println(datasCharacter);

                characterModel.fromMap(datasCharacter);
                System.out.println("33333333333333333333333333333333333");
                System.out.println(characterModel.getName());
                characters.add(characterModel);
                /*
                 * Elements label = docChar.select(".pi-data-label");
                 * Elements value = docChar.select("div .pi-data-value");
                 * 
                 * 
                 * System.out.println("----------------- LABEL -------------------------");
                 * System.out.println("PERSONAGEM: " + character.text());
                 * System.out.println(label.text());
                 * System.out.println("----------------- VALUE -------------------------");
                 * System.out.println(value.text());
                 */
            } catch (Exception e) {
                // TODO: handle exception
            }

        });
        return characters;

    }

}
