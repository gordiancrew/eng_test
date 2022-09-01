package com.kazantsev.eng_test.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    private int id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return getName();
    }
}