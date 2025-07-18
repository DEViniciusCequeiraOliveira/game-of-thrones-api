package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Service.CastlesService;
import com.vinicius.gameofthrones.dto.InstitutionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/castles")
public class CastlesController { 
    @Autowired
    CastlesService castlesService;

    @GetMapping("/region")
    public ResponseEntity<?> getRegionCastles(Pageable page) {
        var castles = castlesService.getCastlesRegion(page);
        return ResponseEntity.ok().body(castles);
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<?> getRegionCastlesId(@PathVariable String id) {
        var castles = castlesService.getCastlesRegionId(id);
        return ResponseEntity.ok().body(castles);
    }

    @GetMapping
    public ResponseEntity<List<InstitutionDTO>> getCastles() {
        var castles = castlesService.getCastles();
        return ResponseEntity.ok().body(castles);
    }
    //@GetMapping
//    public ResponseEntity<List<InstitutionDTO>> getCastlesById() {
//        var castles = castlesService.getCastlesId();
//        return ResponseEntity.ok().body(castles);
//    }
    @GetMapping("id/{name}")
    public ResponseEntity<?> getCastlesByName(@PathVariable String name) {
        var castles = castlesService.getCastlesName(name);
        return ResponseEntity.ok().body(castles);
    }
}