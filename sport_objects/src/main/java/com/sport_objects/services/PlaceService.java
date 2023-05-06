package com.sport_objects.services;

import com.sport_objects.entities.Place;
import com.sport_objects.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> findAll(String searchKeyword) {
        if (searchKeyword == null)
            return placeRepository.findAll();

        return placeRepository.searchKeyword(searchKeyword);
    }

    public void save(Place place) {
        try {
            placeRepository.save(place);
        } catch (Exception e) {

        }
    }

    public void del(Long id) {
        placeRepository.deleteById(id);
    }

    public Place get(Long id) {
        return placeRepository.findById(id).orElse(new Place());
    }

    public void truncate() {
        placeRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        return placeRepository.findById(id).isPresent();
    }

}
