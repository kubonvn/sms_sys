package com.sms_sys.database.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

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

    private Date expiredTimePassword;
}
