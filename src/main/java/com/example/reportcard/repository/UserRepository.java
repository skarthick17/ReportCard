package com.example.reportcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reportcard.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
