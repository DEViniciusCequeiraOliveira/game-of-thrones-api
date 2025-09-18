package com.vinicius.gameofthrones.Models.game;


import org.jsoup.nodes.Element;

import java.util.List;
import java.util.function.Function;

public interface IGameOfThrones {
    public <T> List<T> makeList(List<T> dados, Element dataValue, Function<String, T> creator);
}