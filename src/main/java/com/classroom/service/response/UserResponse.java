package com.classroom.service.response;

import com.classroom.database.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private UserDTO userDTO;
}
