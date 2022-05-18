package com.examonline.service.api;

import com.examonline.business.UserBusiness;
import com.examonline.exception.LogicException;
import com.examonline.service.request.BaseRequest;
import com.examonline.service.request.UserRequest;
import com.examonline.service.response.BaseResponse;
import com.examonline.service.response.UserResponse;
import com.examonline.util.DicHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserServiceImpl{

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private DicHelper dicHelper;

    @Autowired
    private UserBusiness userBusiness;

    @PostMapping("/create")
    public BaseResponse<UserResponse> registerUser(@RequestBody BaseRequest<UserRequest> request)
            throws LogicException {
        try{
            return userBusiness.registerUser(request.getWsRequest().getUserDTO(),
                    request.getWsRequest().getLanguage());
        }catch (LogicException e){
            log.debug("REGISTER USER FUNCTION: "+dicHelper.getMessage(
                    e.getMessage(),request.getWsRequest().getLanguage()
            ));
            return new BaseResponse<UserResponse>(HttpStatus.NO_CONTENT,dicHelper.getMessage(
                    e.getMessage(),request.getWsRequest().getLanguage()
            ));
        }
    }
}
