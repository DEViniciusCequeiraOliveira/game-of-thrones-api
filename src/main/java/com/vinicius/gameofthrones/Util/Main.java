//package com.vinicius.gameofthrones.Util;
//
//import com.vinicius.gameofthrones.Models.StarringModel;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.List;
//
//import static com.vinicius.gameofthrones.Util.ScrapingUtil.currentUrl;
//
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//
//        makeListStarring();
//    }
//
////    public static void makeListStarring(List<StarringModel> dados, Element dataValue) {
//        var value = dataValue.select("div.pi-data-value.pi-font a").select("href");
//        value.forEach(seasonValue -> {
//            Document doc = null;
//            try {
//                doc = Jsoup.connect(currentUrl + seasonValue).get();
//                Elements elements = doc.select("aside.portable-infobox.pi-background.pi-border-color.pi-theme-Westeros.pi-theme-Thrones.pi-layout-default");
//                var name = elements.select("h2.pi-item.pi-item-spacing.pi-title.pi-secondary-background").text();
//                var img = elements.select("figure.pi-item.pi-image a").attr("href");
//
//                var section = elements.select("section.pi-item.pi-group.pi-border-color");
//                var biografia = section.get(0).select("div.pi-data-value.pi-font");
//
//                var nameCompleto = biografia.get(0);
//                var born = biografia.get(1);
//                var lacate = biografia.get(2);
//
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
////        Pattern pattern = Pattern.compile("\\[\\d+\\]");
////
////        value.forEach(seasonValue -> {
////            var txt = seasonValue.text();
////
////            if (pattern.matcher(txt).find()) {
////                return;
////            } else
////                dados.add(creator.apply(txt.replaceAll("\\[\\d+\\]", "")));
////        });
////        return dados;
//    }
//
//
//}
