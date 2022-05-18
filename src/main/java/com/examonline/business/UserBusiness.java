package com.examonline.business;

import com.examonline.database.dto.UserDTO;
import com.examonline.exception.LogicException;
import com.examonline.service.response.BaseResponse;
import com.examonline.service.response.UserResponse;
//hello anh em im wibulord
public interface UserBusiness {
    //create user
    BaseResponse<UserResponse> registerUser(UserDTO user, String language) throws LogicException;
}
