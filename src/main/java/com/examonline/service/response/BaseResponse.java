package com.examonline.service.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
public class BaseResponse<T> extends ResponseEntity<T> {

    private String message;

    public BaseResponse(HttpStatus status) {
        super(status);
    }

    public BaseResponse(HttpStatus status, String message) {
        super(status);
        this.message = message;
    }

    public BaseResponse(T body, HttpStatus status, String message) {
        super(body, status);
        this.message = message;
    }

    public BaseResponse(T body, HttpStatus status) {
        super(body, status);
    }

    public BaseResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public BaseResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public BaseResponse(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}
