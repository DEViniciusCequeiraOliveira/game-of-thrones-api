package com.vinicius.gameofthrones.Repository;

import com.vinicius.gameofthrones.Models.StarringModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarringRepository extends MongoRepository<StarringModel, String> {

}
