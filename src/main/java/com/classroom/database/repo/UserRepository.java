package com.classroom.database.repo;

import com.classroom.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, UserRepositoryCustom{
    Optional<User> findByUsername(String username);
    Optional<User> getAllByUsername(String username);
    Optional<User> getAllByEmail(String email);
    Optional<User> getAllByPhone(String phone);
}
