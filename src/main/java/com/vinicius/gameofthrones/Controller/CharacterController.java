package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Service.CharacterService;
import com.vinicius.gameofthrones.dto.CharacterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/character")
@Tag(name = "Character", description = "Gerencia os personagens de Game of Thrones")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @GetMapping
    @Operation(summary = "Lista todos os personagens", description = "Retorna uma lista paginada com todos os personagens de Game of Thrones.")
    public ResponseEntity<Page<CharacterDTO>> getCharacters(Pageable pageable) {
        return ResponseEntity.ok().body(characterService.getCharacters(pageable));
    }

    @GetMapping("name/{name}")
    @Operation(summary = "Busca personagem por nome", description = "Retorna os detalhes de um personagem específico com base no nome.")
    public ResponseEntity<CharacterDTO> getCharactersByName(@PathVariable String name) {
        return ResponseEntity.ok().body(characterService.getCharactersByName(name));
    }

    @GetMapping("id/{id}")
    @Operation(summary = "Busca personagem por ID", description = "Retorna os detalhes de um personagem específico com base no ID.")
    public ResponseEntity<CharacterDTO> getCharactersById(@PathVariable String id) {
        return ResponseEntity.ok().body(characterService.getMemberById(id));
    }

    @GetMapping("house/{house}")
    @Operation(summary = "Lista personagens por casa", description = "Retorna uma lista paginada de personagens pertencentes a uma casa específica.")
    public ResponseEntity<List<CharacterDTO>> getCharactersByHouse(@PathVariable String house, Pageable pageable) {
        return ResponseEntity.ok().body(characterService.getCharactersByHouse(house, pageable));
    }
}
