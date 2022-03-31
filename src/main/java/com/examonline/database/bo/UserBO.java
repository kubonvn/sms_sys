package com.examonline.database.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBO {
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
