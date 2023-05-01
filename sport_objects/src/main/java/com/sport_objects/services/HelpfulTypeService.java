package com.sport_objects.services;

import com.sport_objects.entities.HelpfulType;
import com.sport_objects.repositories.HelpfulTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpfulTypeService {

    @Autowired
    private HelpfulTypeRepository rep;

    public List<HelpfulType> findAll() {
        return rep.findAll();
    }

    public List<HelpfulType> findAll(String searchKeyword) {
        return rep.searchKeyword(searchKeyword);
    }

    public void save(HelpfulType helpfulType) {
        rep.save(helpfulType);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public HelpfulType get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

}
