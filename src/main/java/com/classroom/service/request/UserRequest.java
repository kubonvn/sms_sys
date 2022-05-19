package com.classroom.service.request;

import com.classroom.database.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private UserDTO userDTO;
    private String captcha;
    private String language;
}
