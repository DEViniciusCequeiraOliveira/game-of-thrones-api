package com.vinicius.gameofthrones.Repository;

import com.vinicius.gameofthrones.Models.MembersModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MemberRepository extends MongoRepository<MembersModel,String> {
}
