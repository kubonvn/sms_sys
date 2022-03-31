package com.examonline.service.response;

import com.examonline.database.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private UserDTO userDTO;
}
