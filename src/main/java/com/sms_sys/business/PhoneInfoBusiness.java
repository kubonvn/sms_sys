package com.sms_sys.business;

import com.sms_sys.database.dto.PhoneInfoDTO;
import com.sms_sys.exception.LogicException;
import com.sms_sys.service.response.BaseResponse;
import com.sms_sys.service.response.PhoneInfoResponse;

public interface PhoneInfoBusiness {
    BaseResponse<PhoneInfoResponse> create(PhoneInfoDTO phoneInfoDTO, String language) throws LogicException;
}
