package com.sms_sys.service.api;

import com.sms_sys.business.UserBusiness;
import com.sms_sys.database.bo.AccessTokenBO;
import com.sms_sys.exception.LogicException;
import com.sms_sys.service.request.BaseRequest;
import com.sms_sys.service.request.UserRequest;
import com.sms_sys.service.response.BaseResponse;
import com.sms_sys.service.response.UserResponse;
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
                    request.getWsRequest().getCaptcha(),
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

    @PostMapping("/getInfo")
    public BaseResponse<UserResponse> getInfomation(@RequestBody BaseRequest<UserRequest> request)
            throws LogicException{
        try{
            return  userBusiness.getInfomation(request.getUsername(),
                    request.getWsRequest().getCaptcha(), request.getWsRequest().getCaptcha());
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
