package com.sms_sys.database.repo;

import com.sms_sys.database.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long>, RoleRepositoryCustom{
    Optional<Role> findAllByRoleName(String roleName);
}
