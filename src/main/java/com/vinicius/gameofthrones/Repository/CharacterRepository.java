package com.vinicius.gameofthrones.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vinicius.gameofthrones.Models.CharacterModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends MongoRepository<CharacterModel, String>{
    Optional<CharacterModel> findByNameIgnoreCase(String name);
    List<CharacterModel> findByHouseIgnoreCase(String house);

}
