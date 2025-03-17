package com.vinicius.gameofthrones.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "castles")
public class CastlesDados{
    @Id
    String _id;
    String name;
    List<CastlesModel> castles;

    public CastlesDados(CastlesDados dados) {
    }

    public void fromMap(Map<String,Object> dados) {
        this.name = (String) dados.get("name");
        this.castles =(List<CastlesModel>)  dados.get("castles");
    }
}
