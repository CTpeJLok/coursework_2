package com.sport_objects.services;

import com.sport_objects.entities.Position;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.models.PositionModel;
import com.sport_objects.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public void save(Position position) throws CreateException {
        try {
            positionRepository.save(position);
        } catch (Exception e) {
            throw new CreateException();
        }
    }

    public void del(Long id) {
        positionRepository.deleteById(id);
    }

    public Position get(Long id) {
        return positionRepository.findById(id).orElse(new Position());
    }

    public void truncate() {
        positionRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        return positionRepository.findById(id).isPresent();
    }

    public List<PositionModel> getAll() {
        List<Position> positions = positionRepository.findAll();
        return positions.stream().map(PositionModel::toModel).toList();
    }

    public PositionModel getOne(Long id) throws NotFoundException {
        Position position = positionRepository.findById(id).get();

        if (position == null)
            throw new NotFoundException();

        return PositionModel.toModel(position);
    }

}
