package com.sport_objects.services;

import com.sport_objects.entities.Role;
import com.sport_objects.entities.Team;
import com.sport_objects.repositories.RoleRepository;
import com.sport_objects.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository rep;

    public List<Role> findAll() {
        return rep.findAll();
    }

    public void save(Role role) {
        rep.save(role);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public Role get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

    public void generateDefaultIfNotExitst() {
        String[] names = {"ROLE_USER", "ROLE_ADMIN"};

        for (String name : names) {
            if (rep.findByName(name) == null)
                rep.save(new Role(name));
        }
    }

    public Role getUserRoleOrCreate() {
        if (rep.findByName("ROLE_USER") == null)
            rep.save(new Role("ROLE_USER"));

        return rep.findByName("ROLE_USER");
    }

    public Role getAdminRoleOrCreate() {
        if (rep.findByName("ROLE_ADMIN") == null)
            rep.save(new Role("ROLE_ADMIN"));

        return rep.findByName("ROLE_ADMIN");
    }

    public boolean isRoleExist(String name) {
        List<Role> roles = rep.findAll();

        for (Role role : roles)
            if (role.getName().equals(name))
                return true;

        return false;
    }

}
