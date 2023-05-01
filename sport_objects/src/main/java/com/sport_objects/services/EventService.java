package com.sport_objects.services;

import com.sport_objects.entities.Event;
import com.sport_objects.entities.Team;
import com.sport_objects.repositories.EventRepository;
import com.sport_objects.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository rep;

    public List<Event> findAll() {
        return rep.findAll();
    }

    public List<Event> findAll(String searchKeyword) {
        return rep.searchKeyword(searchKeyword);
    }

    public void save(Event event) {
        rep.save(event);
    }

    public void del(Long id) {
        rep.deleteById(id);
    }

    public Event get(Long id) {
        return rep.findById(id).get();
    }

    public void truncate() {
        rep.deleteAll();
    }

}
