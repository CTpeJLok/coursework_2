package com.sport_objects.services;

import com.sport_objects.entities.Place;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.models.PlaceModel;
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

    public void save(Place place) throws CreateException {
        try {
            placeRepository.save(place);
        } catch (Exception e) {
            throw new CreateException();
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

    public List<PlaceModel> getAll() {
        List<Place> places = placeRepository.findAll();
        return places.stream().map(PlaceModel::toModel).toList();
    }

    public PlaceModel getOne(Long id) throws NotFoundException {
        Place place = placeRepository.findById(id).get();

        if (place == null)
            throw new NotFoundException();

        return PlaceModel.toModel(place);
    }

}
