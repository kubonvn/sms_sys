package com.sms_sys.service.response;

import com.sms_sys.database.dto.PhoneInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneInfoResponse {
    private PhoneInfoDTO phoneInfoDTO;
}
