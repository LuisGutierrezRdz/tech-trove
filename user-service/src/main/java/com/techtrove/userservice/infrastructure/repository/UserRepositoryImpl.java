package com.techtrove.userservice.infrastructure.repository;

import com.techtrove.userservice.domain.User;
import com.techtrove.userservice.domain.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepositoryImpl implements UserRepository {

    private final MongoUserRepository repository;

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User save(final User user) {
        return repository.save(user);
    }

    @Override
    public void deleteById(final String id) {
        repository.deleteById(id);
    }

}
