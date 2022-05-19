package com.classroom.business;

import com.classroom.database.bo.AccessTokenBO;
import com.classroom.database.dto.UserDTO;
import com.classroom.exception.LogicException;
import com.classroom.service.response.BaseResponse;
import com.classroom.service.response.UserResponse;

public interface UserBusiness {
    //create user
    BaseResponse<UserResponse> registerUser(UserDTO user, String captcha, String language) throws LogicException;
    BaseResponse<AccessTokenBO> getToken(UserDTO user, String language) throws LogicException;
}
