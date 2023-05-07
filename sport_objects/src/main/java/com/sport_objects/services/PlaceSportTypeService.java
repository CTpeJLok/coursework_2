package com.sport_objects.services;

import com.sport_objects.entities.PlaceSportType;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.models.PlaceSportTypeModel;
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

    public void save(PlaceSportType placeSportType) throws CreateException {
        try {
            placeSportTypeRepository.save(placeSportType);
        } catch (Exception e) {
            throw new CreateException();
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

    public List<PlaceSportTypeModel> getAll() {
        List<PlaceSportType> placeSportTypes = placeSportTypeRepository.findAll();
        return placeSportTypes.stream().map(PlaceSportTypeModel::toModel).toList();
    }

    public PlaceSportTypeModel getOne(Long id) throws NotFoundException {
        PlaceSportType placeSportType = placeSportTypeRepository.findById(id).get();

        if (placeSportType == null)
            throw new NotFoundException();

        return PlaceSportTypeModel.toModel(placeSportType);
    }

}
