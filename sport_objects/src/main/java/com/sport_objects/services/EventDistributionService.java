package com.sport_objects.services;

import com.sport_objects.entities.EventDistribution;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.models.EventDistributionModel;
import com.sport_objects.repositories.EventDistributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventDistributionService {

    @Autowired
    private EventDistributionRepository eventDistributionRepository;

    public List<EventDistribution> findAll() {
        return eventDistributionRepository.findAll();
    }

    public List<EventDistribution> findByEventId(Long id) {
        return eventDistributionRepository.findByEventId(id);
    }

    public List<EventDistribution> findByTeamId(Long id) {
        return eventDistributionRepository.findByTeamId(id);
    }

    public List<EventDistribution> findByUserId(Long id) {
        return eventDistributionRepository.findByUserId(id);
    }

    public List<EventDistribution> findByPositionId(Long id) {
        return eventDistributionRepository.findByPositionId(id);
    }

    public void save(EventDistribution eventDistribution) throws CreateException {
        try {
            eventDistributionRepository.save(eventDistribution);
        } catch (Exception e) {
            throw new CreateException();
        }
    }

    public void del(Long id) {
        eventDistributionRepository.deleteById(id);
    }

    public EventDistribution get(Long id) {
        return eventDistributionRepository.findById(id).orElse(new EventDistribution());
    }

    public void truncate() {
        eventDistributionRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        return eventDistributionRepository.findById(id).isPresent();
    }

    public List<EventDistributionModel> getAll() {
        List<EventDistribution> eventDistributions = eventDistributionRepository.findAll();
        return eventDistributions.stream().map(EventDistributionModel::toModel).toList();
    }

    public EventDistributionModel getOne(Long id) throws NotFoundException {
        EventDistribution eventDistribution = eventDistributionRepository.findById(id).get();

        if (eventDistribution == null)
            throw new NotFoundException();

        return EventDistributionModel.toModel(eventDistribution);
    }

}
