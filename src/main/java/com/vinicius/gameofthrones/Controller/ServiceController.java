package com.vinicius.gameofthrones.Controller;

import java.io.IOException;
import java.util.List;

import com.vinicius.gameofthrones.Models.HouseModel;
import com.vinicius.gameofthrones.Repository.HouseRepository;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.gameofthrones.Models.CharacterModel;
import com.vinicius.gameofthrones.Repository.CharacterRepository;
import com.vinicius.gameofthrones.Util.ScrapingUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ServiceController {
    
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    HouseRepository houseRepository;

    @GetMapping(value="/carrega-banco")
    public void carregarBanco() throws IOException {

        //List<CharacterModel> characters = ScrapingUtil.getDados();
        //characterRepository.insert(characters);

        List<HouseModel> houses = ScrapingUtil.getHouse();
        houseRepository.insert(houses);
    }
    
}
