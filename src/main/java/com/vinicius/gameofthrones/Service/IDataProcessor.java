package com.vinicius.gameofthrones.Service;

import org.jsoup.nodes.Element;

import java.util.Map;
import java.util.function.Function;

public interface IDataProcessor {
    void register(String key, Function<Element, Object> handler);

    void process(String data, Element dataValue, Map<String, Object> modelMap);
}
