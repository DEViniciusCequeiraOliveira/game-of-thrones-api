package com.vinicius.gameofthrones.Models.house;

import com.vinicius.gameofthrones.Util.ScrapingUtil;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "house")
public class HouseModel {
    @Id
    private String _id;
    private String name;
    private String coatOfArms;
    private String words;
    private List<HouseRelation> titles;
    private String otherEstates;
    private String seat;
    private String region;
    private String head;
    private String heir;
    private String overlords;
    private List<HouseRelation> vassals;
    private String religion;
    private String founded;

    public void fromMap(Map<String, Object> datasHouse) {
        this.name = ScrapingUtil.removeAscString((String) datasHouse.get("Name"));
        this.coatOfArms = ScrapingUtil.removeAscString((String) datasHouse.get("Coat of arms"));
        this.words = ScrapingUtil.removeAscString((String) datasHouse.get("Words"));
        this.titles = (List<HouseRelation>) datasHouse.get("Title");
        this.otherEstates = ScrapingUtil.removeAscString((String) datasHouse.get("Seat"));
        this.seat = ScrapingUtil.removeAscString((String) datasHouse.get("Seat"));
        this.region = ScrapingUtil.removeAscString((String) datasHouse.get("Region"));
        this.head = ScrapingUtil.removeAscString((String) datasHouse.get("Head"));
        this.heir = ScrapingUtil.removeAscString((String) datasHouse.get("Heir"));
        this.overlords = ScrapingUtil.removeAscString((String) datasHouse.get("Overlords"));
        this.vassals = (List<HouseRelation>) datasHouse.get("Vassals");
        this.religion = ScrapingUtil.removeAscString((String) datasHouse.get("Religion"));
        this.founded = ScrapingUtil.removeAscString((String) datasHouse.get("Founded"));
    }
}
