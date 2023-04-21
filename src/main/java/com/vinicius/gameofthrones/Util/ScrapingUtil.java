package com.vinicius.gameofthrones.Util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vinicius.gameofthrones.Models.BornModel;
import com.vinicius.gameofthrones.Models.CharacterModel;
import com.vinicius.gameofthrones.dto.BornModelDTO;
import com.vinicius.gameofthrones.dto.DiedModelDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrapingUtil {

    final static String urlCharacters = "https://gameofthrones.fandom.com/wiki/Category:Individuals_appearing_in_Game_of_Thrones";
    final static String urlLocation = "https://gameofthrones.fandom.com/wiki/Category:Continents";
    final static String url = "https://gameofthrones.fandom.com/wiki/";

    static List<String> locationList = new ArrayList<>();

    public static List<CharacterModel> getDados() throws IOException {
        List<CharacterModel> characters = new ArrayList<>();

        locationList = getLocation();

        Document doc = Jsoup.connect(urlCharacters).get();
        Elements links = doc.select(".category-page__member-link");
        links.forEach(character -> {
            try {
                Document docChar = Jsoup.connect(url + character.text()).get();
                Elements datas = docChar.select("div .pi-item .pi-data");
                Map<String, Object> datasCharacter = new HashMap<>();

                CharacterModel characterModel = new CharacterModel();

                datasCharacter.put("Name", character.text());
                datas.forEach(data -> {

                    String label = data.select(".pi-data-label").text();
                    // System.out.println(character.text());
                    // System.out.println(label);
                    if (label.equals("Born")) {
                        datasCharacter.put("born", getBorn(data));

                    } else if (label.equals("Died")) {
                        try {
                            datasCharacter.put("died", getDied(data, character));
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        datasCharacter.put(label,
                                data.select("div .pi-data-value").text());
                    }

                });

                characterModel.fromMap(datasCharacter);

                characters.add(characterModel);

            } catch (Exception e) {
                System.out.println("\n------------------------------------");
                System.out.println(character.text());
                System.out.println(urlCharacters + character.text());
                System.out.println(e.getMessage());
                System.out.println("------------------------------------\n");
                // TODO: handle exception
            }

        });

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

    private static List<String> getLocation() throws IOException {
        Document doc = Jsoup.connect(urlLocation).get();
        Elements links = doc.select(".category-page__member-link");
        List<String> locationList = new ArrayList<>();
        links.stream().filter(link -> link.text().contains("Category")).forEach(
                link -> {

                    try {
                        Document docLocation = Jsoup.connect(url + link.text()).get();
                        Elements locationElement = docLocation.select(".category-page__member-link");
                        locationElement.forEach(location -> {

                            if (location.text().contains("Category")) {
                                try {
                                    Document docLocationRev = Jsoup.connect(url + location.text()).get();                                    Elements locationRevElement = docLocationRev.select(".category-page__member-link");
                                    locationRevElement.forEach(loc -> {
                                        locationList.add(loc.text());
});
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                
                            } else {
                                locationList.add(location.text());
                            }

                        }

                );

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                });
        System.out.println(locationList);
        return locationList;
    }
}
