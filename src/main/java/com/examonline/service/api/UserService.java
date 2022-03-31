package com.examonline.service.api;

import com.examonline.exception.LogicException;
import com.examonline.service.request.BaseRequest;
import com.examonline.service.request.UserRequest;
import com.examonline.service.response.BaseResponse;
import com.examonline.service.response.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/user")
public interface UserService {

    @PostMapping("/create")
    public BaseResponse<UserResponse> registerUser(@RequestBody BaseRequest<UserRequest> request)
            throws LogicException;
}