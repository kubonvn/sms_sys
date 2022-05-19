package com.classroom.service.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -9143234037177972208L;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAIL = -1;
    public static final String MSG_SUCCESS = "success";
    public static final String MSG_FAIL = "fail";

    private int code;

    private String message;

    private T objects;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObjects() {
        return objects;
    }

    public void setObjects(T objects) {
        this.objects = objects;
    }

    private BaseResponse(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.objects = builder.data;
    }

    public static Builder ok() {
        return new Builder().code(CODE_SUCCESS).message(MSG_SUCCESS);
    }

    public static <T> Builder ok(T t) {
        return new Builder().code(CODE_SUCCESS).message(MSG_SUCCESS).data(t);
    }

    public static Builder error() {
        return new Builder().code(CODE_FAIL).message(MSG_FAIL);
    }

    public static Builder error(HttpStatus status, String message){
        return new Builder().code(status.value()).message(message);
    }

    public static Builder error(HttpStatus status){
        return new Builder().code(status.value());
    }

    public static Builder success(){
        return new Builder().success();
    }

    public static Builder fail(){
        return new Builder().fail();
    }
    public static class Builder<T> {

        private int code;
        private String message;
        private T data;

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public Builder success() {
            this.code = CODE_SUCCESS;
            this.message = MSG_SUCCESS;
            return this;
        }

        public Builder fail() {
            this.code = CODE_FAIL;
            this.message = MSG_FAIL;
            return this;
        }

        public BaseResponse build() {
            return new BaseResponse(this);
        }
    }
}
