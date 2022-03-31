package com.examonline.database.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO extends BaseDTO {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String about;

    private String email;

    private String phone;

    private Boolean enable;

    private String profile;
}
