package com.techtrove.userservice.domain.respository;

import com.techtrove.userservice.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    User save(User user);

    void deleteById(String id);

}
