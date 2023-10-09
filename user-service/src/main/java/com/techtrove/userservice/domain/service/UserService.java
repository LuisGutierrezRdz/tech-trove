package com.techtrove.userservice.domain.service;

import com.techtrove.userservice.domain.User;

public interface UserService {

    User getUserById(String id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(String id);

}
