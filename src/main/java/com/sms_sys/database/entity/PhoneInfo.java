package com.sms_sys.database.entity;

import com.sms_sys.utils.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "phone_info")
@Table(name = "phone_info", schema = Constant.SCHEMA_ENTITY.SMS_SYS)
public class PhoneInfo extends BaseEntity{

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name =  "auth")
    private String auth;

    @Column(name = "client_id")
    private String clientId;
}
