package com.vinicius.gameofthrones.Repository;

import com.vinicius.gameofthrones.Models.GameOfThronesModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameOfThronesRepository extends MongoRepository<GameOfThronesModel, String> {
}
