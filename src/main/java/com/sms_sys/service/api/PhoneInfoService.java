package com.sms_sys.service.api;

import com.sms_sys.business.PhoneInfoBusiness;
import com.sms_sys.exception.LogicException;
import com.sms_sys.service.request.BaseRequest;
import com.sms_sys.service.request.PhoneInfoRequest;
import com.sms_sys.service.response.BaseResponse;
import com.sms_sys.service.response.PhoneInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/phone_info")
@CrossOrigin("*")
public class PhoneInfoService extends BaseService{

    @Autowired
    private PhoneInfoBusiness phoneInfoBusiness;

    @PostMapping("/create")
    public BaseResponse<PhoneInfoResponse> createPhoneInfo(@RequestBody BaseRequest<PhoneInfoRequest> request)
            throws LogicException {
        try{
            return phoneInfoBusiness.create(request.getWsRequest().getPhoneInfoDTO(),
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
}
