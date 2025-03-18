package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Repository.CharacterRepository;
import com.vinicius.gameofthrones.dto.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;


    public Page<CharacterDTO> getCharacters(Pageable pageable) {
        return characterRepository.findAll(pageable).map(CharacterDTO::new);
    }

    public CharacterDTO getCharactersByName(String name) {
        return characterRepository.findAll()
                .stream()
                .filter(dados -> dados.getName().toLowerCase().contains(name.toLowerCase()))
                .map(CharacterDTO::new).toList()
                .stream().findFirst().get();
    }

    public List<CharacterDTO> getCharactersByHouse(String housesName, Pageable pageable) {
        return characterRepository.findByHouseIgnoreCase(housesName, pageable)
                .stream()
                .map(CharacterDTO::new)
                .collect(Collectors.toList());
    }

    public CharacterDTO getMemberById(String id) {
        return characterRepository.findById(id).map(CharacterDTO::new).get();
    }

}
