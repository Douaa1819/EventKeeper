package com.eventkeeper.dao;

import com.eventkeeper.models.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO {
    private List<Event> events = new ArrayList<>();

    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public void updateEvent(Event event) {
        for (Event e : events) {
            if (e.getId() == event.getId()) {
                e.setTitle(event.getTitle());
                e.setDescription(event.getDescription());
                e.setDate(event.getDate());
                e.setLocation(event.getLocation());
                e.setType(event.getType());
                break;
            }
        }
    }

    @Override
    public void deleteEvent(int eventId) {
        events.removeIf(e -> e.getId() == eventId);
    }

    @Override
    public Event getEventById(int eventId) {
        for (Event e : events) {
            if (e.getId() == eventId) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
}
