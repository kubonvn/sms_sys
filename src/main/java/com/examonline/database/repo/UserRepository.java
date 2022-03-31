package com.examonline.database.repo;

import com.examonline.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, UserRepositoryCustom{
    Optional<User> getAllByUsername(String username);
}
