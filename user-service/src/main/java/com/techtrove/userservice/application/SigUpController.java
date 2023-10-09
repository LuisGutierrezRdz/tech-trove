package com.techtrove.userservice.application;


import com.techtrove.userservice.application.converter.UserConverter;
import com.techtrove.userservice.application.request.CreateUserRequest;
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

@RestController
@RequestMapping("/users/sign-up")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SigUpController {

    private final UserService userService;

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

}
