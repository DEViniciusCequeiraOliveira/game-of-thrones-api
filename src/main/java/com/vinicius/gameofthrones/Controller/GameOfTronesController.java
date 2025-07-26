package com.vinicius.gameofthrones.Controller;

import com.vinicius.gameofthrones.Models.GameOfThronesModel;
import com.vinicius.gameofthrones.Service.GameOfThronesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/serie")
public class GameOfTronesController {
    @Autowired
    GameOfThronesService service;

    @GetMapping
    public GameOfThronesModel getService() throws IOException {
        return service.getGameOfThrones();
    }
    @GetMapping("/delete")
    public String delete() throws IOException {
        return service.delete();
    }
}
