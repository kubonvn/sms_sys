package com.classroom.service.api;

import com.classroom.business.UserBusiness;
import com.classroom.database.bo.AccessTokenBO;
import com.classroom.exception.LogicException;
import com.classroom.service.request.BaseRequest;
import com.classroom.service.request.UserRequest;
import com.classroom.service.response.BaseResponse;
import com.classroom.service.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserService extends BaseService{
    @Autowired
    private UserBusiness userBusiness;

    @PostMapping("/auth/create")
    public BaseResponse<UserResponse> registerUser(@RequestBody BaseRequest<UserRequest> request)
            throws LogicException {
        try{
            return userBusiness.registerUser(request.getWsRequest().getUserDTO(),
                    request.getWsRequest().getCaptcha(),
                    request.getWsRequest().getLanguage());
        }catch (LogicException e){
            log.debug("REGISTER USER FUNCTION: "+dicHelper.getMessage(
                    e.getMessage(),request.getWsRequest().getLanguage()
            ));
            return BaseResponse.error(HttpStatus.FORBIDDEN,dicHelper.getMessage(
                    e.getMessage(),request.getWsRequest().getLanguage()
            )).build();
        }
    }

    @PostMapping("/auth/getToken")
    public BaseResponse<AccessTokenBO> getToken(@RequestBody BaseRequest<UserRequest> request)
            throws LogicException{
        try{
            return  userBusiness.getToken(request.getWsRequest().getUserDTO(),
                    request.getWsRequest().getLanguage());
        }catch (LogicException e){
            log.debug("GET TOKEN FUNCTION:"+dicHelper.getMessage(
                    e.getMessage(),request.getWsRequest().getLanguage()
            ));
            return BaseResponse.error(HttpStatus.FORBIDDEN,dicHelper.getMessage(
                    e.getMessage(),request.getWsRequest().getLanguage()
            )).build();
        }
    }
}
