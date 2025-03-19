package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Service.HouseService;
import com.vinicius.gameofthrones.dto.HouseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/house")
@Tag(name = "House", description = "Gerencia as casas nobres de Game of Thrones")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping
    @Operation(summary = "Lista todas as casas", description = "Retorna uma lista paginada de todas as casas nobres.")
    public ResponseEntity<List<HouseDTO>> getHouse(Pageable pageable) {
        var response = houseService.getHouse(pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca casa por ID", description = "Retorna os detalhes de uma casa específica com base no ID.")
    public ResponseEntity<?> getHouseById(@PathVariable String id) {
        var response = houseService.getHouseById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Busca casa por nome", description = "Retorna os detalhes de uma casa específica com base no nome.")
    public ResponseEntity<?> getHouseByName(@PathVariable String name) {
        var response = houseService.getHouseByName(name);
        return ResponseEntity.ok().body(response);
    }
}
