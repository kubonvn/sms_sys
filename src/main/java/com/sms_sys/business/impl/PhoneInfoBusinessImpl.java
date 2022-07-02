package com.sms_sys.business.impl;

import com.sms_sys.business.PhoneInfoBusiness;
import com.sms_sys.database.dto.PhoneInfoDTO;
import com.sms_sys.database.entity.PhoneInfo;
import com.sms_sys.database.repo.PhoneInfoRepository;
import com.sms_sys.exception.LogicException;
import com.sms_sys.service.response.BaseResponse;
import com.sms_sys.service.response.PhoneInfoResponse;
import com.sms_sys.utils.DataUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneInfoBusinessImpl implements PhoneInfoBusiness {

    private final Logger log = LoggerFactory.getLogger(UserBusinessImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Override
    public BaseResponse<PhoneInfoResponse> create(PhoneInfoDTO phoneInfoDTO, String language) throws LogicException {

        if(DataUtils.isNullObject(phoneInfoDTO)){
            throw new LogicException("phone.info.can.not.null");
        }

        if(DataUtils.isNullOrEmpty(phoneInfoDTO.getUsername())){
            throw new LogicException("username.can.not.null.or.empty");
        }

        if(DataUtils.isNullOrEmpty(phoneInfoDTO.getPassword())){
            throw new LogicException("password.can.not.null.or.empty");
        }

        if(DataUtils.isNullOrEmpty(phoneInfoDTO.getEmail())){
            throw new LogicException("email.can.not.null.or.empty");
        }

        if(DataUtils.isNullOrEmpty(phoneInfoDTO.getNumberPhone())){
            throw new LogicException("number.phone.can.not.null.or.empty");
        }

        if(DataUtils.isNullOrEmpty(phoneInfoDTO.getAuth())){
            throw new LogicException("auth.can.not.null.or.empty");
        }

        if(DataUtils.isNullOrEmpty(phoneInfoDTO.getClientId())){
            throw new LogicException("client.id.can.not.null.or.empty");
        }

        PhoneInfo phoneInfo = modelMapper.map(phoneInfoDTO,PhoneInfo.class);
        phoneInfoRepository.save(phoneInfo);

        PhoneInfoResponse phoneInfoResponse = PhoneInfoResponse.builder()
                .phoneInfoDTO(phoneInfoDTO).build();

        return BaseResponse.ok(phoneInfoResponse).build();
    }
}
