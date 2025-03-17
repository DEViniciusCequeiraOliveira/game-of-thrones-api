package com.vinicius.gameofthrones.dto;

import com.vinicius.gameofthrones.Models.BornModel;
import com.vinicius.gameofthrones.Models.CharacterModel;
import com.vinicius.gameofthrones.Models.DiedModel;
import com.vinicius.gameofthrones.Models.MembersModel;

import java.util.List;

public record MemberDTO(
        String _id,
        String name,
        BornModel born,
        DiedModel died,
        String allegiance,
        String title,
        String culture,
        String father,
        String mother,
        String sibling,
        String series,
        String house,
        String season,
        String appeared,
        String firstSee,
        String lastSee,
        String diedIn,
        String mentioned,
        String portrayed,
        String image) {



    public MemberDTO(CharacterModel dados) {
        this(dados.get_id(),
                dados.getName(),
                dados.getBorn(),
                dados.getDied(),
                dados.getAllegiance(),
                dados.getTitle(),
                dados.getCulture(),
                dados.getFather(),
                dados.getMother(),
                dados.getSibling(),
                dados.getSeries(),
                dados.getHouse(),
                dados.getSeason(),
                dados.getAppeared(),
                dados.getFirstSee(),
                dados.getLastSee(),
                dados.getDiedIn(),
                dados.getMentioned(),
                dados.getPortrayed(),
                dados.getImage());
    }
}
