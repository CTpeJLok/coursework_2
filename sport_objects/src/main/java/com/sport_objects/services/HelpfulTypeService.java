package com.sport_objects.services;

import com.sport_objects.entities.HelpfulType;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.models.HelpfulTypeModel;
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

    public void save(HelpfulType helpfulType) throws CreateException {
        try {
            helpfulTypeRepository.save(helpfulType);
        } catch (Exception e) {
            throw new CreateException();
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

    public List<HelpfulTypeModel> getAll() {
        List<HelpfulType> helpfulTypes = helpfulTypeRepository.findAll();
        return helpfulTypes.stream().map(HelpfulTypeModel::toModel).toList();
    }

    public HelpfulTypeModel getOne(Long id) throws NotFoundException {
        HelpfulType helpfulType = helpfulTypeRepository.findById(id).get();

        if (helpfulType == null)
            throw new NotFoundException();

        return HelpfulTypeModel.toModel(helpfulType);
    }

}
