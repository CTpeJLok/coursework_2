package com.sport_objects.services;

import com.sport_objects.entities.Position;
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

    public void save(Position position) {
        try {
            positionRepository.save(position);
        } catch (Exception e) {

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

}
