package com.vinicius.gameofthrones.Repository;

import com.vinicius.gameofthrones.Models.MembersModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<MembersModel, String> {
    Optional<MembersModel> findByName(String name);
}
