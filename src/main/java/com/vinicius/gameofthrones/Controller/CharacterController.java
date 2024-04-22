package com.vinicius.gameofthrones.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.gameofthrones.Service.CharacterService;
import com.vinicius.gameofthrones.dto.CharacterDTO;

@RestController
@RequestMapping(value = "/character")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @GetMapping
    public ResponseEntity<Page<CharacterDTO>> getCharacters(Pageable pageable) {
        return ResponseEntity.ok().body(characterService.getCharacters(pageable));
    }

}
