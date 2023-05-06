package com.sport_objects.services;

import com.sport_objects.entities.PlaceSportType;
import com.sport_objects.repositories.PlaceSportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceSportTypeService {

    @Autowired
    private PlaceSportTypeRepository placeSportTypeRepository;

    public List<PlaceSportType> findAll() {
        return placeSportTypeRepository.findAll();
    }

    public List<PlaceSportType> findByPlaceId(Long id) {
        return placeSportTypeRepository.findByPlaceId(id);
    }

    public void save(PlaceSportType placeSportType) {
        try {
            placeSportTypeRepository.save(placeSportType);
        } catch (Exception e) {

        }
    }

    public void del(Long id) {
        placeSportTypeRepository.deleteById(id);
    }

    public PlaceSportType get(Long id) {
        return placeSportTypeRepository.findById(id).orElse(new PlaceSportType());
    }

    public void truncate() {
        placeSportTypeRepository.deleteAll();
    }


    public boolean isExist(Long id) {
        return placeSportTypeRepository.findById(id).isPresent();
    }

}
