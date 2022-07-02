package com.sms_sys.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneInfoDTO {

    private String username;

    private String password;

    private String email;

    private String numberPhone;

    private String auth;

    private String clientId;
}
