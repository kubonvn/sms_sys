package com.examonline.service.api.impl;

import com.examonline.business.UserBusiness;
import com.examonline.exception.LogicException;
import com.examonline.service.api.UserService;
import com.examonline.service.request.BaseRequest;
import com.examonline.service.request.UserRequest;
import com.examonline.service.response.BaseResponse;
import com.examonline.service.response.UserResponse;
import com.examonline.util.DicHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private DicHelper dicHelper;

    @Autowired
    private UserBusiness userBusiness;

    @Override
    public BaseResponse<UserResponse> registerUser(BaseRequest<UserRequest> request)
            throws LogicException {
        try{
            return userBusiness.registerUser(request.getWsRequest().getUserDTO(),
                    request.getWsRequest().getLanguage());
        }catch (LogicException e){
            return new BaseResponse<UserResponse>(HttpStatus.NO_CONTENT,dicHelper.getMessage(
                    e.getMessage(),request.getWsRequest().getLanguage()
            ));
        }
    }
}
