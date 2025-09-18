package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Repository.*;
import com.vinicius.gameofthrones.Util.ScrapingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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

    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private StarringRepository starringRepository;

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
        var model = util.GameOfThrones();
        var modelStarring = starringRepository.insert(model.getStarring());
        model.setStarring(modelStarring);
        gameOfThronesRepository.insert(model);
    }

    public void saveSeason() throws IOException {
        List<String> links = List.of("https://gameofthrones.fandom.com/wiki/Game_of_Thrones:_Season_1",
                "https://gameofthrones.fandom.com/wiki/Game_of_Thrones:_Season_2",
                "https://gameofthrones.fandom.com/wiki/Game_of_Thrones:_Season_3",
                "https://gameofthrones.fandom.com/wiki/Game_of_Thrones:_Season_4",
                "https://gameofthrones.fandom.com/wiki/Game_of_Thrones:_Season_5",
                "https://gameofthrones.fandom.com/wiki/Game_of_Thrones:_Season_6",
                "https://gameofthrones.fandom.com/wiki/Game_of_Thrones:_Season_7",
                "https://gameofthrones.fandom.com/wiki/Game_of_Thrones:_Season_8");

        for (String link : links) {
            seasonRepository.insert(util.season(link));
        }
    }

    public void saveAll() throws IOException {
        characterRepository.insert(util.getCharacter());
        houseRepository.insert(util.getHouse());
        castlesRepository.insert(util.getCastles());
        memberRepository.insert(util.getMember());
        saveGameOfThrones();
        saveSeason();
    }
}
