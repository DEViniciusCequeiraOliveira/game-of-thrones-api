package com.vinicius.gameofthrones.Repository;

import com.vinicius.gameofthrones.Models.CastlesDados;
import com.vinicius.gameofthrones.Models.CastlesModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CastlesRepository extends MongoRepository<CastlesDados,String> {
    List<CastlesDados> findAllBy(Pageable pageable);
     @Query("{ 'nome' : ?0 }")
     List<CastlesDados> findByNomeLike(String nome);
}
