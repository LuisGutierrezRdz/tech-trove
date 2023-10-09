package com.techtrove.userservice.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techtrove.userservice.application.converter.UserConverter;
import com.techtrove.userservice.domain.Role;
import com.techtrove.userservice.domain.User;
import com.techtrove.userservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(UserController.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private User user;
    private String expectedResponse;

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

        expectedResponse = objectMapper.writeValueAsString(UserConverter.INSTANCE.toResponse(user));
    }

    @Test
    @WithMockUser
    void getUserById() throws Exception {

        when(userService.getUserById(any())).thenReturn(user);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/v1/users/{id}", "6521edebb9ae5f1b1d01d5a8")
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser
    void updateUser() throws Exception {

        when(userService.getUserById(any())).thenReturn(user);
        when(userService.updateUser(any())).thenReturn(user);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/v1/users/{id}", "6521edebb9ae5f1b1d01d5a8")
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser
    void deleteUserById() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/v1/users/{id}", "6521edebb9ae5f1b1d01d5a8")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }

}
