package com.sport_objects.services;

import com.sport_objects.entities.HelpfulType;
import com.sport_objects.repositories.HelpfulTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpfulTypeService {

    @Autowired
    private HelpfulTypeRepository helpfulTypeRepository;

    public List<HelpfulType> findAll(String searchKeyword) {
        if (searchKeyword == null)
            return helpfulTypeRepository.findAll();

        return helpfulTypeRepository.searchKeyword(searchKeyword);
    }

    public void save(HelpfulType helpfulType) {
        try {
            helpfulTypeRepository.save(helpfulType);
        } catch (Exception e) {

        }
    }

    public void del(Long id) {
        helpfulTypeRepository.deleteById(id);
    }

    public HelpfulType get(Long id) {
        return helpfulTypeRepository.findById(id).orElse(new HelpfulType());
    }

    public void truncate() {
        helpfulTypeRepository.deleteAll();
    }


    public boolean isExist(Long id) {
        return helpfulTypeRepository.findById(id).isPresent();
    }

}
