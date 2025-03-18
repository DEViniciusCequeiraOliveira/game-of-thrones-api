package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Service.CharacterService;
import com.vinicius.gameofthrones.dto.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/character")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @GetMapping
    public ResponseEntity<Page<CharacterDTO>> getCharacters(Pageable pageable) {
        return ResponseEntity.ok().body(characterService.getCharacters(pageable));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<CharacterDTO> getCharactersByName(@PathVariable String name) {
        return ResponseEntity.ok().body(characterService.getCharactersByName(name));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<CharacterDTO> getCharactersById(@PathVariable String id) {
        return ResponseEntity.ok().body(characterService.getMemberById(id));
    }

    @GetMapping("house/{house}")
    public ResponseEntity<List<CharacterDTO>> getCharactersByHouse(@PathVariable String house, Pageable pageable) {
        return ResponseEntity.ok().body(characterService.getCharactersByHouse(house, pageable));
    }

}
