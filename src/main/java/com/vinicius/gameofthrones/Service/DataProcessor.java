package com.vinicius.gameofthrones.Service;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class DataProcessor implements IDataProcessor {

    private final Map<String, Function<Element, Object>> handlers = new HashMap<>();

    public void register(String key, Function<Element, Object> handler) {
        handlers.put(key, handler);
    }

    public void process(String data, Element dataValue, Map<String, Object> modelMap) {
        Function<Element, Object> handler = handlers.get(data);

        if (handler != null) {
            Object result = handler.apply(dataValue);
            modelMap.put(data, result);
        } else {
            // Default case (sem handler definido)
            String value = dataValue.select("div.pi-data-value.pi-font").text();
            System.out.println("____________________");
            System.out.println(data + " " + value);
            modelMap.put(data, removeAscString(value));
        }
    }

    // Você pode mover isso para uma classe de utilitários se quiser
    private String removeAscString(String value) {
        return value.replaceAll("[^\\x00-\\x7F]", "").trim(); // Exemplo: remove acentos e símbolos estranhos
    }
}
