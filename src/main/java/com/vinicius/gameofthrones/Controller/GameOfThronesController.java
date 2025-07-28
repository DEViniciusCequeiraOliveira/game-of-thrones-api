package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Models.GameOfThronesModel;
import com.vinicius.gameofthrones.Service.GameOfThronesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/serie")
public class GameOfThronesController {
    @Autowired
    GameOfThronesService service;

    @GetMapping
    public ResponseEntity<GameOfThronesModel> getGameOfThronesEndpoint() throws IOException {
        GameOfThronesModel model = service.getGameOfThrones();
        return ResponseEntity.ok(model);
    }
    @DeleteMapping
    public String delete() throws IOException {
        return service.delete();
    }
}
