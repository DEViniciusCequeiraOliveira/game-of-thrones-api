package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Models.GameOfThronesModel;
import com.vinicius.gameofthrones.Models.Season.SeasonPreview; // Importar SeasonPreview
import com.vinicius.gameofthrones.Repository.GameOfThronesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GameOfThronesService {
    @Autowired
    GameOfThronesRepository repository;

    public GameOfThronesModel getGameOfThrones() throws IOException {
        GameOfThronesModel gameOfThrones = repository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("GameOfThrones não encontrado"));

        return gameOfThrones;
    }

    public String delete() {
        repository.deleteAll();
        return "Deletado com sucesso";
    }

    public GameOfThronesModel find(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("GameOfThrones com ID " + id + " não encontrado"));
    }
}