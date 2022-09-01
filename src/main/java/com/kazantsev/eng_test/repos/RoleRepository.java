package com.kazantsev.eng_test.repos;

import com.kazantsev.eng_test.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Integer> {
}
