package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Repository.CastlesRepository;
import com.vinicius.gameofthrones.Repository.CharacterRepository;
import com.vinicius.gameofthrones.Repository.HouseRepository;
import com.vinicius.gameofthrones.Repository.MemberRepository;
import com.vinicius.gameofthrones.Util.ScrapingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServiceUtil {

    ScrapingUtil util = new ScrapingUtil();
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CastlesRepository castlesRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private CharacterRepository characterRepository;

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

    public void saveAll() throws IOException {
        characterRepository.insert(util.getCharacter());
        houseRepository.insert(util.getHouse());
        castlesRepository.insert(util.getCastles());
        memberRepository.insert(util.getMember());

    }
}
