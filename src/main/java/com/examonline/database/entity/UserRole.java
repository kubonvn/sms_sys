package com.examonline.database.entity;

import com.examonline.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@Entity(name = "user_role")
@Table(name = "user_role",schema = Constant.SCHEMA_ENTITY.EXAM_ONLINE_DB)
public class UserRole extends BaseEntity{

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToOne
   @JoinColumn(name = "role_id")
   private Role role;

    public UserRole() {

    }
}
