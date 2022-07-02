package com.sms_sys.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReCaptchaResponse {
    private boolean success;
    private String challenge_ts;
    private String hostName;
}
