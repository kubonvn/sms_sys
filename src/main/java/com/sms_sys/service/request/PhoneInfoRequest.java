package com.sms_sys.service.request;

import com.sms_sys.database.dto.PhoneInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneInfoRequest {
    private PhoneInfoDTO phoneInfoDTO;
    private String language;
}
