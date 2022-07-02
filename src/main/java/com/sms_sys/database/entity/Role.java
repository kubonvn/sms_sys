package com.sms_sys.database.entity;

import com.sms_sys.utils.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity(name = "role")
@Table(name = "role", schema = Constant.SCHEMA_ENTITY.SMS_SYS)
public class Role extends BaseEntity{
    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission", joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<Permission> permissions = new HashSet<>();

    public Role() {
    }
}
