package com.vinicius.gameofthrones.dto;

import com.vinicius.gameofthrones.Models.CharacterModel;
import com.vinicius.gameofthrones.Models.MembersModel;

import java.util.List;

public record MemberDTO(
        String _id,
        String name,
        List<CharacterModel>members) {
    public MemberDTO(MembersModel dados) {
        this(dados.get_id(),
                dados.getName(),
                dados.getMembers());
    }
}
