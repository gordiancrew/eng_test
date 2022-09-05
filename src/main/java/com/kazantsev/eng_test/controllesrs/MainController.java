package com.kazantsev.eng_test.controllesrs;

import com.kazantsev.eng_test.entities.Role;
import com.kazantsev.eng_test.entities.User;
import com.kazantsev.eng_test.repos.RoleRepository;
import com.kazantsev.eng_test.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

@Controller
public class MainController {
    private final String ROLE_USER = "ROLE_USER";
    private final String ROLE_ADMIN = "ROLE_ADMIN";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/creater")
    public String creater() {
        roleRepository.deleteAll();
        userRepository.deleteAll();
        Role roleAdmin = new Role();
        roleAdmin.setId(1);
        roleAdmin.setName(ROLE_ADMIN);
        Role roleUser = new Role();
        roleUser.setId(2);
        roleUser.setName(ROLE_USER);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        User user = new User();
        user.setUsername("user");
        user.setPassword("$2a$10$lIroGsPzn5bsKwFa1TN3a.IlUDRwUOMe9qJ1gYpiAGEN1QeSGIpxG");
        user.setRoles(Collections.singleton(roleUser));

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("$2a$10$lIroGsPzn5bsKwFa1TN3a.IlUDRwUOMe9qJ1gYpiAGEN1QeSGIpxG");
        admin.setRoles(Collections.singleton(roleAdmin));
        userRepository.save(user);
        userRepository.save(admin);
        return "index";
    }
}
