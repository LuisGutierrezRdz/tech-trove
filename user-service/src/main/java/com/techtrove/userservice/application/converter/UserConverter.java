package com.techtrove.userservice.application.converter;


import com.techtrove.userservice.application.request.CreateUserRequest;
import com.techtrove.userservice.application.request.UpdateUserRequest;
import com.techtrove.userservice.application.response.UserResponse;
import com.techtrove.userservice.domain.Role;
import com.techtrove.userservice.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

@Mapper(imports = {ZonedDateTime.class, Role.class})
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper( UserConverter.class );

    @Mapping(target = "role", expression = "java(Role.USER)")
    @Mapping(target = "createdAt", expression = "java(ZonedDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(ZonedDateTime.now())")
    User toUser(CreateUserRequest request);

    UserResponse toResponse(User user);

    @Mapping(target = "role", expression = "java(Role.USER)")
    void update(@MappingTarget User user, UpdateUserRequest updateUserRequest);

}
