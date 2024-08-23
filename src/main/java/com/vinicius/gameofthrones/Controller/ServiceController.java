package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Models.CastlesDados;
import com.vinicius.gameofthrones.Repository.CastlesRepository;
import com.vinicius.gameofthrones.Repository.CharacterRepository;
import com.vinicius.gameofthrones.Repository.HouseRepository;
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

    @GetMapping(value="/carrega-banco")
    public void carregarBanco() throws IOException {

        //List<CharacterModel> characters = ScrapingUtil.getDados();
        //characterRepository.insert(characters);

//        List<HouseModel> houses = ScrapingUtil.getHouse();
//        houseRepository.insert(houses);

        List<CastlesDados> castles = ScrapingUtil.getCastles();
        castlesRepository.insert(castles);


    }
    
}
