package com.sport_objects.services;

import com.sport_objects.entities.PlaceHelpfulType;
import com.sport_objects.repositories.PlaceHelpfulTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceHelpfulTypeService {

    @Autowired
    private PlaceHelpfulTypeRepository rep;

    public List<PlaceHelpfulType> findAll() {
        return rep.findAll();
    }

    public List<PlaceHelpfulType> findPlaceHelpfulTypesByPlaceId(Long id) {
        return rep.findPlaceHelpfulTypesByPlaceId(id);
    }

    public void save(PlaceHelpfulType placeHelpfulType) {
        rep.save(placeHelpfulType);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public PlaceHelpfulType get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

}
