package com.examonline.database.entity;

import com.examonline.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user", schema = Constant.SCHEMA_ENTITY.EXAM_ONLINE_DB)
public class User extends BaseEntity{
    @Column(name = "username")
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

    public User() {
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();
}
