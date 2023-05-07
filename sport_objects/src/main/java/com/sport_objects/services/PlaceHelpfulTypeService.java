package com.sport_objects.services;

import com.sport_objects.entities.PlaceHelpfulType;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.models.PlaceHelpfulTypeModel;
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

    public void save(PlaceHelpfulType placeHelpfulType) throws CreateException {
        try {
            placeHelpfulTypeRepository.save(placeHelpfulType);
        } catch (Exception e) {
            throw new CreateException();
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

    public List<PlaceHelpfulTypeModel> getAll() {
        List<PlaceHelpfulType> placeHelpfulTypes = placeHelpfulTypeRepository.findAll();
        return placeHelpfulTypes.stream().map(PlaceHelpfulTypeModel::toModel).toList();
    }

    public PlaceHelpfulTypeModel getOne(Long id) throws NotFoundException {
        PlaceHelpfulType placeHelpfulType = placeHelpfulTypeRepository.findById(id).get();

        if (placeHelpfulType == null)
            throw new NotFoundException();

        return PlaceHelpfulTypeModel.toModel(placeHelpfulType);
    }

}
