package com.vinicius.gameofthrones.Util;

import com.vinicius.gameofthrones.Models.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class ScrapingUtil {
    final static String urlCastles = "https://gameofthrones.fandom.com/wiki/Category:Castles";
    final static String urlCharacters = "https://gameofthrones.fandom.com/wiki/Category:Individuals_appearing_in_Game_of_Thrones";
    final static String urlHouses = "https://gameofthrones.fandom.com/wiki/Category:Great_Houses";
    final static String urlLocation = "https://gameofthrones.fandom.com/wiki/Category:Continents";
    final static String currentUrl = "https://gameofthrones.fandom.com";
    final static String url = "https://gameofthrones.fandom.com/";
    private static Set<String> processedLinks = new HashSet<>();
    private static Set<String> locationList = new HashSet<>();

    final static List<String> LOCATION_STRINGS = new ArrayList<>(
            Arrays.asList("Category:Bay of Seals", "Category:Skagos", "Category:Gift"));

    public static String removeAscString(String texto) {
        if (texto != null) {
            return texto.replaceAll("\\[\\d+\\]", "").trim().replaceAll("[\\[\\]{}\"]", "").trim();
        }
        return "";
    }

    public static List<CharacterModel> getDados() throws IOException {
        List<CharacterModel> characters = new ArrayList<>();
        locationList = getLocation(urlLocation);
        String currentUrl = urlCharacters;
        Document doc;

        // Loop de paginação
        while (currentUrl != null) {
            doc = Jsoup.connect(currentUrl).get();
            Elements links = doc.select(".category-page__member-link");
            links.forEach(character -> {
                try {
                    Document docChar = Jsoup.connect(url + character.attr("href")).get();
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
                            datasCharacter.put(label, data.select("div .pi-data-value").text());
                        }
                    });

                    characterModel.fromMap(datasCharacter);
                    characters.add(characterModel);


                } catch (Exception e) {
                    System.out.println("\n------------------------------------");
                    System.out.println(character.text());
                    System.out.println(url + character.attr("href"));
                    System.out.println(e.getMessage());
                    System.out.println("------------------------------------\n");
                }
            });

            // Verifica se existe um botão "Próxima Página" para continuar a navegação
            Element nextPageButton = doc.selectFirst(".category-page__pagination-next");
            if (nextPageButton != null) {
                String nextPageHref = nextPageButton.attr("href");
                currentUrl = "https://gameofthrones.fandom.com" + nextPageHref;
            } else {
                currentUrl = null;
            }

        }

        return characters;
    }

    // essa merda funciona mas não mepeia os dados 100%
    private static BornModel getBorn(Element data) {
        // Seleciona todos os elementos <a> dentro do div com a classe pi-data-value
        Elements bornElements = data.select("div.pi-data-value.pi-font a");
        Map<String, String> bornCharacter = new HashMap<>();

        // Verifica se há pelo menos um <a> com atributo title para o ano de nascimento
        if (bornElements.size() > 0) {
            // Extrai o ano de nascimento do primeiro <a> com atributo title
            String year = bornElements.get(0).attr("title");
            bornCharacter.put("Timeline", year);
        } else {
            bornCharacter.put("Timeline", "unknown");
        }

        StringBuilder location = new StringBuilder();
        Pattern removePattern = Pattern.compile("\\[.*?]");

        for (int i = 1; i < bornElements.size(); i++) {
            String text = bornElements.get(i).text();
            text = removePattern.matcher(text).replaceAll("");
            if (!text.trim().isEmpty()) {
                if (location.length() > 0) {
                    location.append(", ");
                }
                location.append(text.trim());
            }
        }

        bornCharacter.put("Local", location.length() > 0 ? location.toString() : "unknown");

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

    private static Set<String> getLocation(String urlLocation) throws IOException {
        Set<String> locations = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(urlLocation);

        while (!queue.isEmpty()) {
            String currentUrl = queue.poll();
            if (processedLinks.contains(currentUrl)) {
                continue;
            }
            processedLinks.add(currentUrl);
            try {
                Document doc = Jsoup.connect(currentUrl).ignoreHttpErrors(true).get();
                Elements links = doc.select(".category-page__member-link");

                for (Element link : links) {
                    String linkText = link.text();
                    String linkUrl = url + link.attr("href");  // Formar URL completa
                    if (linkText.contains("Category") && !linkText.contains("culture")) {
                        if (!LOCATION_STRINGS.contains(linkText)) {
                            queue.add(linkUrl);
                        } else {
                            locations.add(linkText.replace("Category:", ""));
                        }
                    } else if (!linkText.contains("Category") && !locations.contains(linkText)) {
                        locations.add(linkText);
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to fetch URL: " + currentUrl);
                e.printStackTrace();
            }
        }

        return locations;
    }

    public static List<HouseModel> getHouse() throws IOException {
        List<HouseModel> houses = new ArrayList<>();
        Document doc = Jsoup.connect(urlHouses).get();
        Elements datas = doc.select(".category-page__member-link");

        datas.forEach(house -> {
            try {
                Document docChar = Jsoup.connect(currentUrl + house.attr("href")).get();
                Elements data = docChar.select("div.pi-item.pi-data");
                Map<String, Object> datasHouse = new HashMap<>();

                // Criação do modelo da casa
                HouseModel houseModel = new HouseModel();

                datasHouse.put("Image", docChar.select(".pi-image-thumbnail").attr("src"));
                datasHouse.put("Name", house.text());

                // Itera sobre cada dado relevante da casa
                data.forEach(element -> {
                    String label = element.select("h3.pi-data-label").text();
                    // deifinir na ordem do elemetos do site
                    formatarElementos("Title", "Titles", element, label, datasHouse);
                    formatarElementos("Vassals", "Vassals", element, label, datasHouse);

                });

                houseModel.fromMap(datasHouse);
                if (houseModel.getName().contains("Category")) {
                    return;
                }
                houses.add(houseModel);

            } catch (Exception e) {
                System.out.println("\n------------------------------------");
                System.out.println(house.text());
                System.out.println(currentUrl + house.attr("href"));
                System.out.println(e.getMessage());
                System.out.println("------------------------------------\n");

            }
        });
        return houses;

    }

    public static List<CastlesDados> getCastles() throws IOException {
        final String url = "https://gameofthrones.fandom.com";
        List<CastlesDados> castlesList = new ArrayList<>();
        Document doc = Jsoup.connect(urlCastles).get();
        Elements links = doc.select(".category-page__member-link");
        //Categoria de regioes de Westeros
        links.forEach(link -> {
            try {
                Document docChar = Jsoup.connect(url + link.attr("href")).get();
                String categoryCastlesName = link.text();
                Map<String, Object> castlesMap = new HashMap<>();
                castlesMap.put("name", categoryCastlesName);

                Elements links2 = docChar.select(".category-page__member-link");
                List<CastlesModel> castlesModels = new ArrayList<>();
                //Regioes de Westeros
                links2.forEach(element -> {
                    try {
                        Document docChar2 = Jsoup.connect(url + element.attr("href")).get();
                        Map<String, Object> castlesObj = new HashMap<>();
                        castlesObj.put("name", element.text());
                        // Castelos da regiao
                        Elements details = docChar2.select(".pi-item");
                        for (Element detail : details) {
                            String label = detail.select("h3.pi-data-label").text();
                            String value = detail.select("div.pi-data-value.pi-font").text();
                            castlesObj.put(label, value);
                        }

                        CastlesModel castlesModel = new CastlesModel();
                        castlesModel.fromMap(castlesObj);
                        castlesModels.add(castlesModel);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                castlesMap.put("castles", castlesModels);
                CastlesDados castlesDados = new CastlesDados();
                castlesDados.fromMap(castlesMap);
                castlesList.add(castlesDados);
            } catch (Exception e) {
                System.out.println("\n------------------------------------");
                System.out.println(link.text());
                System.out.println(currentUrl + link.attr("href"));
                System.out.println(e.getMessage());
                System.out.println("------------------------------------\n");
            }
        });
        return castlesList;
    }

private static void formatarElementos(String nome, String element, Element data, String label, Map<String, Object> datasHouse) {
    // cria objetos para melhorar a formataçao dos elementos
    if (label.equals(element)) {
        Elements dados = data.select("div.pi-data-value.pi-font a");
        List<GeralModel> geralModel = new ArrayList<>();

        dados.forEach(dado -> {
            // cria um objeto para cada casa
            GeralModel vm = new GeralModel();
            vm.setName(dado.text());
            vm.setLabel(label);
            geralModel.add(vm);
        });
        // Adiciona a lista de items ao mapa
        datasHouse.put(nome, geralModel);
    } else {
        // Adiciona outros dados ao mapa
        String value = data.select("div.pi-data-value").text();
        datasHouse.put(label, value);
    }
}
}
