package com.vinicius.gameofthrones.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vinicius.gameofthrones.Repository.CharacterRepository;
import com.vinicius.gameofthrones.dto.CharacterDTO;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public Page<CharacterDTO> getCharacters(Pageable pageable) {
        return characterRepository.findAll(pageable).map(CharacterDTO::new);
    }

}
