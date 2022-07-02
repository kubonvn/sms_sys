package com.sms_sys.database.entity;

import com.sms_sys.utils.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "permission")
@Table(name = "permission", schema = Constant.SCHEMA_ENTITY.EXAM_ONLINE_DB)
public class Permission extends BaseEntity{
    @Column(length = 20)
    private String name;
}
