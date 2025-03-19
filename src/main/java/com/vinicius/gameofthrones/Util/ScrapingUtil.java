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
    final static String currentUrl = "https://gameofthrones.fandom.com";
    final static String url = "https://gameofthrones.fandom.com/";

    final static List<String> LOCATION_STRINGS = new ArrayList<>(
            Arrays.asList("Category:Bay of Seals", "Category:Skagos", "Category:Gift"));

    public static String removeAscString(String texto) {
        if (texto != null) {
            return texto.replaceAll("\\[\\d+\\]", "").trim().replaceAll("[\\[\\]{}\"]", "").trim();
        }
        return "";
    }

    public List<CharacterModel> getCharacter() throws IOException {
        try {
            List<CharacterModel> characters = new ArrayList<>();
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
                    currentUrl = nextPageHref;
                    System.out.println(currentUrl);
                } else {
                    currentUrl = null;
                }
            }
            return characters;
        } catch (Exception e) {
            System.out.println("\n------------------------------------");
            System.out.println(currentUrl);
            System.out.println(e.getMessage());
            System.out.println("------------------------------------\n");
        }
        return null;
    }

    // essa merda funciona mas não mepeia os dados 100%
    private static BornModel getBorn(Element data) {

        Elements bornElements = data.select("div.pi-data-value.pi-font a");
        Map<String, String> bornCharacter = new HashMap<>();

        if (!bornElements.isEmpty()) {
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
                if (!location.isEmpty()) {
                    location.append(", ");
                }
                location.append(text.trim());
            }
        }

        bornCharacter.put("Local", !location.isEmpty() ? location.toString() : "unknown");

        BornModel bornModel = new BornModel();
        bornModel.fromMap(bornCharacter);
        return bornModel;
    }

    private static DiedModel getDied(Element data) {
        Set<String> locationList = new HashSet<>();
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


    public List<HouseModel> getHouse() throws IOException {
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

    public List<CastlesDados> getCastles() throws IOException {
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

    public List<MembersModel> getMember() throws IOException {
        var link = "https://gameofthrones.fandom.com/wiki/Category:Individuals_by_affiliation";
        return processeLink(link);
    }

    public static List<MembersModel> processeLink(String urlLocation) throws IOException {
        List<MembersModel> list = new ArrayList<>();

        Document doc = Jsoup.connect(urlLocation).get();
        Elements links = doc.select(".category-page__member-link");

        if (!links.isEmpty()) {
            links.forEach(link -> {
                try {
                    String href = link.attr("href");
                    String title = link.attr("title");

                    if (title.contains("Members")) {
                        String fullUrl = url + href;
                        list.add(getMemberCharacter(fullUrl));
                        processeLink(fullUrl);
                    } else {
                        String nextUrl = url + href;
                        processeLink(nextUrl);
                    }
                } catch (Exception e) {
                    System.out.println("\n------------------------------------");
                    System.out.println(link.text());
                    System.out.println(url + link.attr("href"));
                    System.out.println(e.getMessage());
                    System.out.println("------------------------------------\n");
                }
            });
        }
        return list;
    }

    public static MembersModel getMemberCharacter(String ur) throws IOException {
        // Cria instâncias de membros e listas
        HashMap<String, Object> objeto = new HashMap<>();
        List<CharacterModel> characterModelList = new ArrayList<>();

        // Obtém o documento da URL fornecida
        Document doc = Jsoup.connect(ur).get();

        // Obtém o nome da casa
        String houseName = doc.selectFirst(".page-header__title").text();
        objeto.put("name", houseName);

        // Seleciona os links dos membros
        Elements links = doc.select(".category-page__member-link");

        // Itera sobre os links de membros e processa cada um
        for (Element link : links) {
            String memberUrl = url + link.attr("href");
            try {
                Document docChar = Jsoup.connect(memberUrl).get();
                Elements datas = docChar.select("div .pi-item .pi-data");

                // Cria um mapa para armazenar os dados do personagem
                Map<String, Object> datasCharacter = new HashMap<>();
                datasCharacter.put("Image", docChar.select(".pi-image-thumbnail").attr("src"));
                datasCharacter.put("Name", docChar.selectFirst(".page-header__title").text()); // Nome do personagem

                // Processa os dados do personagem
                for (Element data : datas) {
                    String label = data.select(".pi-data-label").text();
                    String value = data.select("div .pi-data-value").text();
                    if (label.equals("Born")) {
                        datasCharacter.put("Born", getBorn(data));
                    } else if (label.equals("Died")) {
                        datasCharacter.put("Died", getDied(data));
                    } else {
                        datasCharacter.put(label, value);
                    }
                }
                // Cria e adiciona o CharacterModel à lista
                CharacterModel characterModel = new CharacterModel();
                characterModel.fromMap(datasCharacter);
                characterModelList.add(characterModel);

            } catch (Exception e) {
                System.out.println("\n------------------------------------");
                System.out.println(link.text());
                System.out.println(url + link.attr("href"));
                System.out.println(e.getMessage());
                System.out.println("------------------------------------\n");
            }
        }

        objeto.put("members", characterModelList);

        MembersModel membersModel = new MembersModel();
        membersModel.fromMap(objeto);

        return membersModel;
    }


    private static void formatarElementos(String nome, String element, Element data, String label, Map<String, Object> datasHouse) {
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
