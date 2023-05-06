package com.sport_objects.services;

import com.sport_objects.entities.SportType;
import com.sport_objects.repositories.SportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportTypeService {

    @Autowired
    private SportTypeRepository sportTypeRepository;

    public List<SportType> findAll(String searchKeyword) {
        if (searchKeyword == null)
            return sportTypeRepository.findAll();

        return sportTypeRepository.searchKeyword(searchKeyword);
    }

    public void save(SportType sportType) {
        try {
            sportTypeRepository.save(sportType);
        } catch (Exception e) {

        }
    }

    public void del(Long id) {
        sportTypeRepository.deleteById(id);
    }

    public SportType get(Long id) {
        return sportTypeRepository.findById(id).orElse(new SportType());
    }

    public void truncate() {
        sportTypeRepository.deleteAll();
    }


    public boolean isExist(Long id) {
        return sportTypeRepository.findById(id).isPresent();
    }

}
