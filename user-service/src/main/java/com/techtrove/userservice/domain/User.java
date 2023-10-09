package com.techtrove.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

    @Id
    private String id;

    @Indexed
    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private Role role;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
