package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Models.GameOfThronesModel;
import com.vinicius.gameofthrones.Models.Season.SeasonPreview; // Importar SeasonPreview
import com.vinicius.gameofthrones.Repository.GameOfThronesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GameOfThronesService {
    @Autowired
    GameOfThronesRepository repository;

    // Método para buscar uma SeasonPreview por ID (crucial para o link "self" da temporada)
    public SeasonPreview findSeasonById(String seasonId) {
        Optional<GameOfThronesModel> optionalGoT = repository.findAll().stream().findFirst();
        if (optionalGoT.isPresent()) {
            return optionalGoT.get().getSeason().stream()
                    .filter(s -> s.get_id() != null && s.get_id().equals(seasonId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public GameOfThronesModel getGameOfThrones() throws IOException {
        GameOfThronesModel gameOfThrones = repository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("GameOfThrones não encontrado"));

        gameOfThrones.add(linkTo(methodOn(GameOfThronesService.class).getGameOfThrones()).withSelfRel());
        gameOfThrones.add(linkTo(methodOn(GameOfThronesService.class).delete()).withRel("delete_all"));

        gameOfThrones.getSeason().forEach(season -> {
            if (season.get_id() != null) {
                season.add(linkTo(methodOn(GameOfThronesService.class).findSeasonById(season.get_id())).withSelfRel());
            }
        });

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