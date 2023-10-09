package com.techtrove.userservice.domain.service;

import com.techtrove.userservice.domain.User;
import com.techtrove.userservice.domain.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @Override
    public User createUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(prod -> {throw new ResponseStatusException(HttpStatus.CONFLICT, "User Already Exist"); });

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {

         return userRepository.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        var user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        userRepository.deleteById(user.getId());
    }
}
