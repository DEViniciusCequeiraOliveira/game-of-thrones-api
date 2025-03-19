package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Service.CastlesService;
import com.vinicius.gameofthrones.dto.InstitutionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/castles")
@Tag(name = "Castles", description = "Gerencia os castelos do mundo de Game of Thrones")
public class CastlesController {

    @Autowired
    CastlesService castlesService;

    @GetMapping("/region")
    @Operation(summary = "Lista castelos por região", description = "Retorna uma lista paginada de castelos pertencentes a cada região.")
    public ResponseEntity<?> getRegionCastles(Pageable page) {
        var castles = castlesService.getCastlesRegion(page);
        return ResponseEntity.ok().body(castles);
    }

    @GetMapping("/region/id/{id}")
    @Operation(summary = "Busca castelos por ID de região", description = "Retorna a lista de castelos pertencentes a uma região específica com base no ID.")
    public ResponseEntity<?> getRegionCastlesId(@PathVariable String id) {
        var castles = castlesService.getCastlesRegionId(id);
        return ResponseEntity.ok().body(castles);
    }

    @GetMapping
    @Operation(summary = "Lista todos os castelos", description = "Retorna uma lista de todos os castelos cadastrados.")
    public ResponseEntity<List<InstitutionDTO>> getCastles() {
        var castles = castlesService.getCastles();
        return ResponseEntity.ok().body(castles);
    }

    @GetMapping("id/{name}")
    @Operation(summary = "Busca castelo por nome", description = "Retorna os detalhes de um castelo específico com base no nome.")
    public ResponseEntity<?> getCastlesByName(@PathVariable String name) {
        var castles = castlesService.getCastlesName(name);
        return ResponseEntity.ok().body(castles);
    }
}
