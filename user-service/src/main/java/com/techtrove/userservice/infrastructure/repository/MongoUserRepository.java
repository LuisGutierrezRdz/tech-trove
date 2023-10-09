package com.techtrove.userservice.infrastructure.repository;

import com.techtrove.userservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoUserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String sku);

}
