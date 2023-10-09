package com.techtrove.userservice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techtrove.userservice.domain.Role;
import com.techtrove.userservice.domain.User;
import com.techtrove.userservice.infrastructure.repository.MongoUserRepository;
import com.techtrove.userservice.infrastructure.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @Mock
    private MongoUserRepository mongoUserRepository;

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
    void findUserById() {

        var userOpt = Optional.of(user);

        when(mongoUserRepository.findById(any())).thenReturn(userOpt);

        final var result = userRepositoryImpl.findById("6521edebb9ae5f1b1d01d5a8");

        verify(mongoUserRepository).findById(any());

        assertEquals(userOpt, result);
    }

    @Test
    void findUserBySKU() {

        var userOpt = Optional.of(user);

        when(mongoUserRepository.findByEmail(any())).thenReturn(userOpt);

        final var result = userRepositoryImpl.findByEmail("123445");

        verify(mongoUserRepository).findByEmail(any());

        assertEquals(userOpt, result);
    }

    @Test
    void saveUser() {

        when(mongoUserRepository.save(any())).thenReturn(user);

        final var result = userRepositoryImpl.save(user);

        verify(mongoUserRepository).save(any());

        assertEquals(user, result);
    }

    @Test
    void deleteUserById() {

        userRepositoryImpl.deleteById("6521edebb9ae5f1b1d01d5a8");

        verify(mongoUserRepository).deleteById(any());
    }
}