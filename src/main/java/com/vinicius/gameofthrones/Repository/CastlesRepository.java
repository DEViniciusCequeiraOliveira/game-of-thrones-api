package com.vinicius.gameofthrones.Repository;

import com.vinicius.gameofthrones.Models.CastlesDados;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CastlesRepository extends MongoRepository<CastlesDados,String> {
}
