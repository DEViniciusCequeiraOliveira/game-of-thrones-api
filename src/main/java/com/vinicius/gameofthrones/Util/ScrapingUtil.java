package com.vinicius.gameofthrones.Util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vinicius.gameofthrones.Models.BornModel;
import com.vinicius.gameofthrones.Models.CharacterModel;
import com.vinicius.gameofthrones.dto.BornModelDTO;
import com.vinicius.gameofthrones.dto.DiedModelDTO;

import ch.qos.logback.core.joran.conditional.ElseAction;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrapingUtil {

    final static String urlCharacters = "https://gameofthrones.fandom.com/wiki/Category:Individuals_appearing_in_Game_of_Thrones";
    final static String urlLocation = "https://gameofthrones.fandom.com/wiki/Category:Continents";
    final static String url = "https://gameofthrones.fandom.com/wiki/";
    final static List<String> LOCATION_STRINGS = new ArrayList<>(
            Arrays.asList("Category:Bay of Seals", "Category:Skagos"));
    
    static List<String> locationList = new ArrayList<>();

    public static List<CharacterModel> getDados() throws IOException {
        List<CharacterModel> characters = new ArrayList<>();

        locationList = getLocation(urlLocation);

        /*
         * 
         * Document doc = Jsoup.connect(urlCharacters).get();
         * Elements links = doc.select(".category-page__member-link");
         * links.forEach(character -> {
         * try {
         * Document docChar = Jsoup.connect(url + character.text()).get();
         * Elements datas = docChar.select("div .pi-item .pi-data");
         * Map<String, Object> datasCharacter = new HashMap<>();
         * 
         * CharacterModel characterModel = new CharacterModel();
         * 
         * datasCharacter.put("Name", character.text());
         * datas.forEach(data -> {
         * 
         * String label = data.select(".pi-data-label").text();
         * // System.out.println(character.text());
         * // System.out.println(label);
         * if (label.equals("Born")) {
         * datasCharacter.put("born", getBorn(data));
         * 
         * } else if (label.equals("Died")) {
         * try {
         * datasCharacter.put("died", getDied(data, character));
         * } catch (IOException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         * } else {
         * datasCharacter.put(label,
         * data.select("div .pi-data-value").text());
         * }
         * 
         * });
         * 
         * characterModel.fromMap(datasCharacter);
         * 
         * characters.add(characterModel);
         * 
         * } catch (Exception e) {
         * System.out.println("\n------------------------------------");
         * System.out.println(character.text());
         * System.out.println(urlCharacters + character.text());
         * System.out.println(e.getMessage());
         * System.out.println("------------------------------------\n");
         * // TODO: handle exception
         * }
         * 
         * });
         */

        return characters;

    }

    private static BornModelDTO getBorn(Element data) {
        Elements bornElements = data.select("div .pi-data-value").get(0).children().select("a");
        Map<String, String> bornCharacter = new HashMap<>();

        bornCharacter.put("Timeline", bornElements.select("[title=\"Timeline\"]").text());
        bornCharacter.put("Local", bornElements.last().text());

        BornModelDTO bornModelDTO = new BornModelDTO();
        bornModelDTO.fromMap(bornCharacter);
        // System.out.println("Born: " + bornCharacter);
        return bornModelDTO;
    }

    private static DiedModelDTO getDied(Element data, Element character) throws IOException {
        Elements diedElements = data.select("div .pi-data-value").get(0).children().select("a");
        Map<String, String> diedCharacter = new HashMap<>();

        diedCharacter.put("Timeline", diedElements.select("[title=\"Timeline\"]").text());
        if (diedElements.size() > 1) {
            diedCharacter.put("Local",
                    locationList.contains(diedElements.get(1).text()) ? diedElements.get(1).text() : "");
        }

        DiedModelDTO diedModelDTO = new DiedModelDTO();
        diedModelDTO.fromMap(diedCharacter);

        System.out.println(character.text() + " Died: " + diedCharacter);
        return diedModelDTO;
    }

    private static List<String> getLocation(String urlLocation) throws IOException {
        Document doc = Jsoup.connect(urlLocation).get();
        Elements links = doc.select(".category-page__member-link");

        links.forEach(link -> {
            try {
                if (link.text().contains("Category") && !link.text().contains("culture")) {
                    if (!LOCATION_STRINGS.contains(link.text())) {
                        getLocation(url + link.text());
                    } else {
                        locationList.add(link.text().replace("Category:", ""));
                    }

                } else if (!link.text().contains("Category") && !locationList.contains(link.text())) {
                    locationList.add(link.text());
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        });

        System.out.println(locationList);
        return locationList;
    }
}
