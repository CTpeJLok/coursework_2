package com.example.sport_objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SportTypeService {

    @Autowired
    private SportTypeRepository rep;

    private final Random rnd = new Random();

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
