package com.vinicius.gameofthrones.Repository;

import com.vinicius.gameofthrones.Models.HouseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseRepository extends MongoRepository<HouseModel,String> {
    Optional<HouseModel> findByName(String name);
}
