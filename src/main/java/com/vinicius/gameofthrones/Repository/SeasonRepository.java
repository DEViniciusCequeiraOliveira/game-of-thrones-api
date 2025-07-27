package com.vinicius.gameofthrones.Repository;

import com.vinicius.gameofthrones.Models.Season.SeasonModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeasonRepository extends MongoRepository<SeasonModel, String> {
}
