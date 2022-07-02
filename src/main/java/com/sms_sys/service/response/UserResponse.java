package com.sms_sys.service.response;

import com.sms_sys.database.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private UserDTO userDTO;
}
