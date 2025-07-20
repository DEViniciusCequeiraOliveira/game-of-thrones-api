package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Repository.*;
import com.vinicius.gameofthrones.Util.ScrapingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServiceUtil {
    @Autowired
    private ScrapingUtil util;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CastlesRepository castlesRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private CharacterRepository characterRepository;
@Autowired
    private GameOfThronesRepository gameOfThronesRepository;

    public void saveMember() throws IOException {
        memberRepository.insert(util.getMember());
    }

    public void saveCastles() throws IOException {
        castlesRepository.insert(util.getCastles());
    }

    public void saveHose() throws IOException {
        houseRepository.insert(util.getHouse());
    }

    public void saveCharacter() throws IOException {
        characterRepository.insert(util.getCharacter());
    }
    public void saveGameOfThrones() throws IOException {
        gameOfThronesRepository.insert(util.GameOfThrones());
    }

    public void saveAll() throws IOException {
        characterRepository.insert(util.getCharacter());
        houseRepository.insert(util.getHouse());
        castlesRepository.insert(util.getCastles());
        memberRepository.insert(util.getMember());
        saveGameOfThrones();
    }
}
