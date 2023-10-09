package com.techtrove.userservice.application;


import com.techtrove.userservice.application.converter.UserConverter;
import com.techtrove.userservice.application.request.CreateUserRequest;
import com.techtrove.userservice.application.request.UpdateUserRequest;
import com.techtrove.userservice.application.response.UserResponse;
import com.techtrove.userservice.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get user by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "404", description = "User not found.")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable final String id) {

        final var user = userService.getUserById(id);

        return UserConverter.INSTANCE.toResponse(user);
    }

    @Operation(summary = "Create user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid fields in request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "409", description = "User already exist.")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody final CreateUserRequest request) {

        final var user = userService.createUser(UserConverter.INSTANCE.toUser(request));

        return UserConverter.INSTANCE.toResponse(user);
    }

    @Operation(summary = "Update user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "404", description = "User not found.")
    })
    @PutMapping(value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable final String id,
                                      @Valid @RequestBody final UpdateUserRequest request) {

        final var user = userService.getUserById(id);
        user.setUpdatedAt(ZonedDateTime.now());
        UserConverter.INSTANCE.update(user, request);
        final var updatedUser = userService.updateUser(user);

        return UserConverter.INSTANCE.toResponse(updatedUser);
    }

    @Operation(summary = "Delete user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully."),
            @ApiResponse(responseCode = "404", description = "User not found.")
    })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable final String id) {

        userService.deleteUserById(id);
    }

}
