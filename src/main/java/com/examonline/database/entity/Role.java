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
@AllArgsConstructor
@Entity(name = "role")
@Table(name = "role", schema = Constant.SCHEMA_ENTITY.EXAM_ONLINE_DB)
public class Role extends BaseEntity{
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    public Role() {
    }
}
