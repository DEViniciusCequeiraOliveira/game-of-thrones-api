package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Models.IGameOfThrones;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

@Component
public class HtmlListMapper implements IGameOfThrones {

    @Override
    public <T> List<T> makeList(List<T> dados, Element dataValue, Function<String, T> creator) {
        var value = dataValue.select("div.pi-data-value.pi-font").select("a");
        Pattern pattern = Pattern.compile("\\[\\d+\\]");

        value.forEach(seasonValue -> {
            var txt = seasonValue.text();

            if (pattern.matcher(txt).find()) {
                return;
            } else
                dados.add(creator.apply(txt.replaceAll("\\[\\d+\\]", "")));
        });
        return dados;
    }
}
