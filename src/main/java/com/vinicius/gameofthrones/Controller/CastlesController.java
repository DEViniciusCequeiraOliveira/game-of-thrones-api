package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Repository.CastlesRepository;
import com.vinicius.gameofthrones.dto.CastlesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/castles")
public class CastlesController {
    @Autowired
    CastlesRepository castlesRepository;

    @GetMapping
    public ResponseEntity<Page<CastlesDTO>> getCastles(Pageable page) {
        var castles = castlesRepository.findAll(page).map(CastlesDTO::new);
        return ResponseEntity.ok().body(castles);
    }
}
