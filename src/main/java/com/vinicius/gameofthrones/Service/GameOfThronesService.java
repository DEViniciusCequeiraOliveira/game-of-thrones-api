package com.vinicius.gameofthrones.Service;

import com.vinicius.gameofthrones.Models.GameOfThronesModel;
import com.vinicius.gameofthrones.Repository.GameOfThronesRepository;
import com.vinicius.gameofthrones.Util.ScrapingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GameOfThronesService {
    @Autowired
    GameOfThronesRepository repository;

    public GameOfThronesModel getGameOfThrones() throws IOException {
        return repository.findAll().get(0);
    }
}
