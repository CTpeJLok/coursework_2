package com.sport_objects.services;

import com.sport_objects.entities.EventPlace;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.models.EventPlaceModel;
import com.sport_objects.repositories.EventPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventPlaceService {

    @Autowired
    private EventPlaceRepository eventPlaceRepository;

    public List<EventPlace> findAll(String searchKeyword) {
        if (searchKeyword == null)
            return eventPlaceRepository.findAll();

        return eventPlaceRepository.searchKeyword(searchKeyword);
    }

    public List<EventPlace> findByEventId(Long id) {
        return eventPlaceRepository.findByEventId(id);
    }

    public void save(EventPlace eventPlace) throws CreateException {
        try {
            eventPlaceRepository.save(eventPlace);
        } catch (Exception e) {
            throw new CreateException();
        }
    }

    public void del(Long id) {
        eventPlaceRepository.deleteById(id);
    }

    public EventPlace get(Long id) {
        return eventPlaceRepository.findById(id).orElse(new EventPlace());
    }

    public void truncate() {
        eventPlaceRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        return eventPlaceRepository.findById(id).isPresent();
    }

    public List<EventPlaceModel> getAll() {
        List<EventPlace> eventPlaces = eventPlaceRepository.findAll();
        return eventPlaces.stream().map(EventPlaceModel::toModel).toList();
    }

    public EventPlaceModel getOne(Long id) throws NotFoundException {
        EventPlace eventPlace = eventPlaceRepository.findById(id).get();

        if (eventPlace == null)
            throw new NotFoundException();

        return EventPlaceModel.toModel(eventPlace);
    }

}
