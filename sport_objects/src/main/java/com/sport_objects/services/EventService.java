package com.sport_objects.services;

import com.sport_objects.entities.Event;
import com.sport_objects.exceptions.NotFoundException;
import com.sport_objects.exceptions.CreateException;
import com.sport_objects.models.EventModel;
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

    public void save(Event event) throws CreateException {
        try {
            eventRepository.save(event);
        } catch (Exception e) {
            throw new CreateException();
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

    public List<EventModel> getAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventModel::toModel).toList();
    }

    public EventModel getOne(Long id) throws NotFoundException {
        Event event = eventRepository.findById(id).get();

        if (event == null)
            throw new NotFoundException();

        return EventModel.toModel(event);
    }

}
