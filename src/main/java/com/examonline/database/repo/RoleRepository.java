package com.examonline.database.repo;

import com.examonline.database.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long>, RoleRepositoryCustom{
    Optional<Role> findAllByRoleName(String roleName);
}
