package com.vinicius.gameofthrones.Models;

import com.vinicius.gameofthrones.Util.ScrapingUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
@Setter
public class CastlesModel {

    String name;
    String type;
    String location;
    String rules;
    String religion;
    String placeNotes;
    String founded;
    String status;

    public void fromMap(Map<String, Object> dados) {
        this.name = ScrapingUtil.removeAscString((String) dados.get("name"));
        this.type = ScrapingUtil.removeAscString((String) dados.get("Type"));
        this.placeNotes = ScrapingUtil.removeAscString((String) dados.get("Places of note"));
        this.location = ScrapingUtil.removeAscString((String) dados.get("Location"));
        this.rules = ScrapingUtil.removeAscString((String) dados.get("Rules"));
        this.religion = ScrapingUtil.removeAscString((String) dados.get("Religion"));
        this.founded = ScrapingUtil.removeAscString((String) dados.get("Founded"));
        this.status = ScrapingUtil.removeAscString((String) dados.get("Status"));
    }
}
