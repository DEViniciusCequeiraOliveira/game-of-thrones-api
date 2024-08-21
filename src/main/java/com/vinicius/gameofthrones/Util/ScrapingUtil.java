package com.vinicius.gameofthrones.Util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cglib.core.Local;

import com.vinicius.gameofthrones.Models.BornModel;
import com.vinicius.gameofthrones.Models.CharacterModel;
import com.vinicius.gameofthrones.Models.DiedModel;
import com.vinicius.gameofthrones.Models.LocalModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrapingUtil {

    final static String urlCharacters = "https://gameofthrones.fandom.com/wiki/Category:Individuals_appearing_in_Game_of_Thrones";
    final static String urlLocation = "https://gameofthrones.fandom.com/wiki/Category:Continents";
    final static String url = "https://gameofthrones.fandom.com/wiki/";

    static List<LocalModel> locationList = new ArrayList<>();
    // Essas localizações precisam ser declaradas pois eelas geram um loop infinito
    final static List<String> LOCATION_STRINGS = new ArrayList<>(
            Arrays.asList("Category:Bay of Seals", "Category:Skagos", "Category:Gift"));

    public static String removeAscString(String texto) {
        if (texto != null) {
            return texto = texto.replaceAll("\\[\\d+\\]", "").trim().replaceAll("[\\[\\]{}\"]", "").trim();
        }
        return "";
    }

    public static List<CharacterModel> getDados() throws IOException {
        List<CharacterModel> characters = new ArrayList<>();

        locationList = getLocation(urlLocation);

        Document doc = Jsoup.connect(urlCharacters).get();
        Elements links = doc.select(".category-page__member-link");

        links.forEach(character -> {
            try {
                Document docChar = Jsoup.connect(url + character.text()).get();
                Elements datas = docChar.select("div .pi-item .pi-data");
                Map<String, Object> datasCharacter = new HashMap<>();

                CharacterModel characterModel = new CharacterModel();
                
                
                datasCharacter.put("Image", docChar.select(".pi-image-thumbnail").attr("src"));
                datasCharacter.put("Name", character.text());
                datas.forEach(data -> {

                    String label = data.select(".pi-data-label").text();
                    if (label.equals("Born")) {
                        datasCharacter.put("Born", getBorn(data));

                    } else if (label.equals("Died")) {
                        datasCharacter.put("Died", getDied(data));
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
                System.out.println(url + character.text());
                System.out.println(e.getMessage());
                System.out.println("------------------------------------\n");
                // TODO: handle exception
            }

        });

        return characters;

    }

    private static BornModel getBorn(Element data) {
        Elements bornElements = data.select("div .pi-data-value").get(0).children().select("a");
        Map<String, String> bornCharacter = new HashMap<>();

        bornCharacter.put("Timeline", bornElements.select("[title=\"Timeline\"]").text());

        if (bornElements.size() > 1) {
            bornCharacter.put("Local",
                    locationList.contains(bornElements.get(1).text()) ? bornElements.get(1).text() : "");
        }

        BornModel bornModel = new BornModel();

        bornModel.fromMap(bornCharacter);
        return bornModel;
    }

    private static DiedModel getDied(Element data) {
        Elements diedElements = data.select("div .pi-data-value").get(0).children().select("a");
        Map<String, String> diedCharacter = new HashMap<>();

        diedCharacter.put("Timeline", diedElements.select("[title=\"Timeline\"]").text());
        if (diedElements.size() > 1) {
            diedCharacter.put("Local",
                    locationList.contains(diedElements.get(1).text()) ? diedElements.get(1).text() : "");
        }

        DiedModel diedModel = new DiedModel();
        diedModel.fromMap(diedCharacter);

        return diedModel;
    }

    private static List<LocalModel> getLocation(String urlLocation) throws IOException {
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
        return locationList;
    }
}
