package com.sms_sys.database.entity;

import com.sms_sys.utils.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user", schema = Constant.SCHEMA_ENTITY.SMS_SYS)
public class User extends BaseEntity{
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "about")
    private String about;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "enable")
    private Boolean enable = true;

    @Column(name = "profile")
    private String profile;

    @Column(name = "expired_time_password")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredTimePassword;

    public User() {
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
}
