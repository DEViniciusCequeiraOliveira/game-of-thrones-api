package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Models.CharacterModel;
import com.vinicius.gameofthrones.Models.MembersModel;

import java.util.List;

public record MemberHouseDTO(
        String _id,
        String name,
        List<CharacterModel> members) {

    public MemberHouseDTO(MembersModel dados) {
        this(dados.get_id(),
                dados.getName(),
                dados.getMembers());
    }
}
