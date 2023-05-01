package com.sport_objects.services;

import com.sport_objects.entities.PlaceSportType;
import com.sport_objects.repositories.PlaceSportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceSportTypeService {

    @Autowired
    private PlaceSportTypeRepository rep;

    public List<PlaceSportType> findAll() {
        return rep.findAll();
    }

    public List<PlaceSportType> findByPlaceID(Long id) {
        return rep.findPlaceSportTypesByPlaceId(id);
    }

    public void save(PlaceSportType placeSportType) {
        rep.save(placeSportType);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public PlaceSportType get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

}
