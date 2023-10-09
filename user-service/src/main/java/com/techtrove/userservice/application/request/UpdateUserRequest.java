package com.techtrove.userservice.application.request;

import com.techtrove.userservice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private Role role;

}
