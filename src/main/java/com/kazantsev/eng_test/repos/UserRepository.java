package com.kazantsev.eng_test.repos;

import com.kazantsev.eng_test.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
