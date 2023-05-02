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

}
