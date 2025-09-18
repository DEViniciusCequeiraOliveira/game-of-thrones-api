package com.vinicius.gameofthrones.Models;

import com.vinicius.gameofthrones.Models.characters.CharacterModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "members")
public class MembersModel {
    @Id
    private String _id;
    private String name;
    private List<CharacterModel> members;


    public void fromMap(HashMap<String, Object> dados) {
        this.name = (String) dados.get("name");
        this.members = (List<CharacterModel>) dados.get("members");
    }
}
