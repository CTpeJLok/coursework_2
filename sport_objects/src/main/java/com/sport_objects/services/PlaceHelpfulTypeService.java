package com.sport_objects.services;

import com.sport_objects.entities.PlaceHelpfulType;
import com.sport_objects.repositories.PlaceHelpfulTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceHelpfulTypeService {

    @Autowired
    private PlaceHelpfulTypeRepository placeHelpfulTypeRepository;

    public List<PlaceHelpfulType> findAll() {
        return placeHelpfulTypeRepository.findAll();
    }

    public List<PlaceHelpfulType> findByPlaceId(Long id) {
        return placeHelpfulTypeRepository.findByPlaceId(id);
    }

    public void save(PlaceHelpfulType placeHelpfulType) {
        try {
            placeHelpfulTypeRepository.save(placeHelpfulType);
        } catch (Exception e) {

        }
    }

    public void del(Long id) {
        placeHelpfulTypeRepository.deleteById(id);
    }

    public PlaceHelpfulType get(Long id) {
        return placeHelpfulTypeRepository.findById(id).orElse(new PlaceHelpfulType());
    }

    public void truncate() {
        placeHelpfulTypeRepository.deleteAll();
    }


    public boolean isExist(Long id) {
        return placeHelpfulTypeRepository.findById(id).isPresent();
    }

}
