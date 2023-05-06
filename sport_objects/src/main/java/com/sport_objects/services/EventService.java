package com.sport_objects.services;

import com.sport_objects.entities.Event;
import com.sport_objects.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll(String searchKeyword) {
        if (searchKeyword == null)
            return eventRepository.findAll();

        return eventRepository.searchKeyword(searchKeyword);
    }

    public void save(Event event) {
        try {
            eventRepository.save(event);
        } catch (Exception e) {

        }
    }

    public void del(Long id) {
        eventRepository.deleteById(id);
    }

    public Event get(Long id) {
        return eventRepository.findById(id).orElse(new Event());
    }

    public void truncate() {
        eventRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        return eventRepository.findById(id).isPresent();
    }

}
