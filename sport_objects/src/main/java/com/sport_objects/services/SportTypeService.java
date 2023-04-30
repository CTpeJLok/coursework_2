package com.sport_objects.services;

import com.sport_objects.entities.SportType;
import com.sport_objects.repositories.SportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportTypeService {

    @Autowired
    private SportTypeRepository rep;

    public List<SportType> findAll() {
        return rep.findAll();
    }

    public List<SportType> findAll(String searchKeyword) {
        return rep.searchKeyword(searchKeyword);
    }

    public void save(SportType sportType) {
        rep.save(sportType);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public SportType get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

}
