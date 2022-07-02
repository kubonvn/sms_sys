package com.sms_sys.business;

import com.sms_sys.database.bo.AccessTokenBO;
import com.sms_sys.database.dto.UserDTO;
import com.sms_sys.exception.LogicException;
import com.sms_sys.service.response.BaseResponse;
import com.sms_sys.service.response.UserResponse;

public interface UserBusiness {
    //create user
    BaseResponse<UserResponse> registerUser(UserDTO user, String captcha, String language) throws LogicException;
    BaseResponse<AccessTokenBO> getToken(UserDTO user, String captcha,String language) throws LogicException;
    BaseResponse<UserResponse> getInfomation(String username,String captcha, String language) throws LogicException;
}
