package com.sport_objects.services;

import com.sport_objects.entities.EventPlace;
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

    public void save(EventPlace eventPlace) {
        try {
            eventPlaceRepository.save(eventPlace);
        } catch (Exception e) {

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

}
