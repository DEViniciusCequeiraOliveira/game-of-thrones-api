package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Models.CharacterModel;
import com.vinicius.gameofthrones.Models.MembersModel;
import com.vinicius.gameofthrones.Repository.CastlesRepository;
import com.vinicius.gameofthrones.Repository.CharacterRepository;
import com.vinicius.gameofthrones.Repository.HouseRepository;
import com.vinicius.gameofthrones.Repository.MemberRepository;
import com.vinicius.gameofthrones.Util.ScrapingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
public class ServiceController {

    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    CastlesRepository castlesRepository;
    @Autowired
    MemberRepository membeRepository;

    @GetMapping(value = "/carrega-banco")
    public String carregarBanco() throws IOException {
        final String individualsUrl = "https://gameofthrones.fandom.com/wiki/Category:Individuals_by_affiliation";

        List<CharacterModel> characters = ScrapingUtil.getCharacter();
        characterRepository.insert(characters);

        System.out.println("characters ok");
//
//        List<HouseModel> houses = ScrapingUtil.getHouse();
//        houseRepository.insert(houses);
//
//        System.out.println("houses ok");
//
//        List<CastlesDados> castles = ScrapingUtil.getCastles();
//        castlesRepository.insert(castles);
//
//        System.out.println("castles ok");

        List<MembersModel> members = ScrapingUtil.processeLink(individualsUrl);
        membeRepository.insert(members);

        System.out.println("members ok");

        return "End";

    }


}
