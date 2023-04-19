package com.vinicius.gameofthrones.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vinicius.gameofthrones.Models.CharacterModel;

@Repository
public interface CharacterRepository extends MongoRepository<CharacterModel, String>{
    
}
