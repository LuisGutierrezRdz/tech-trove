package com.techtrove.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techtrove.userservice.domain.Role;
import com.techtrove.userservice.domain.User;
import com.techtrove.userservice.domain.respository.UserRepository;
import com.techtrove.userservice.domain.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void init() throws JsonProcessingException {
        user = User.builder()
                .id("6523262fdf346f233146a543")
                .email("example@company.com")
                .firstName("FirstName")
                .lastName("LastName")
                .phone("+524758691256")
                .role(Role.USER)
                .build();
    }

    @Test
    void getUserByIdSuccessfully() {

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        final var result = userServiceImpl.getUserById("6521edebb9ae5f1b1d01d5a8");

        verify(userRepository).findById(any());

        assertEquals(user, result);
    }

    @Test
    void getUserByIdMistakenly() {

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () ->  userServiceImpl.getUserById("6521edebb9ae5f1b1d01d5a8"));
    }

    @Test
    void createUserSuccessfully() {

        when(userRepository.save(any())).thenReturn(user);

        final var result = userServiceImpl.createUser(user);

        verify(userRepository).save(any());

        assertEquals(user, result);
    }

    @Test
    void createUserMistakenly() {

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));

        assertThrows(ResponseStatusException.class,
                () ->  userServiceImpl.createUser(user));
    }

    @Test
    void updateUserSuccessfully() {

        when(userRepository.save(any())).thenReturn(user);

        final var result = userServiceImpl.updateUser(user);

        verify(userRepository).save(any());

        assertEquals(user, result);
    }

    @Test
    void deleteUserByIdSuccessfully() {

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        userServiceImpl.deleteUserById("6521edebb9ae5f1b1d01d5a8");

        verify(userRepository).findById(any());
    }

    @Test
    void deleteUserByIdMistakenly() {

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () ->  userServiceImpl.deleteUserById("6521edebb9ae5f1b1d01d5a8"));
    }
}
