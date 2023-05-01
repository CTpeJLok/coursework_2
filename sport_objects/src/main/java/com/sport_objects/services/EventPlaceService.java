package com.sport_objects.services;

import com.sport_objects.entities.EventPlace;
import com.sport_objects.repositories.EventPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventPlaceService {

    @Autowired
    private EventPlaceRepository rep;

    public List<EventPlace> findAll() {
        return rep.findAll();
    }

    public List<EventPlace> findEventPlacesByEventId(Long id) {
        return rep.findEventPlacesByEventId(id);
    }

    public void save(EventPlace eventPlace) {
        rep.save(eventPlace);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public EventPlace get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

}
