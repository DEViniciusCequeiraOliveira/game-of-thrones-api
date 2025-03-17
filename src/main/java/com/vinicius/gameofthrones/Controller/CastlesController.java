package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Repository.CastlesRepository;
import com.vinicius.gameofthrones.Service.CastlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/castles")
public class CastlesController {
    @Autowired
    CastlesService castlesService;


    @GetMapping("/region")
    public ResponseEntity<?> getRegionCastles(Pageable page) {
        var castles = castlesService.getCastlesRegion();
        return ResponseEntity.ok().body(castles);
    }

    @GetMapping
    public ResponseEntity<?> getCastles(Pageable page) {
        var castles = castlesService.getCastles();
        return ResponseEntity.ok().body(castles);
    }
}
