package com.vinicius.gameofthrones.Repository;

import com.vinicius.gameofthrones.Models.CastlesDados;
import com.vinicius.gameofthrones.Models.CastlesModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CastlesRepository extends MongoRepository<CastlesDados,String> {

}
