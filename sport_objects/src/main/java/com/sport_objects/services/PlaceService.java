package com.sport_objects.services;

import com.sport_objects.entities.Place;
import com.sport_objects.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository rep;

    public List<Place> findAll() {
        return rep.findAll();
    }

    public List<Place> findAll(String searchKeyword) {
        return rep.searchKeyword(searchKeyword);
    }

    public void save(Place place) {
        rep.save(place);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public Place get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

}
