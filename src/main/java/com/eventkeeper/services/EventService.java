package com.eventkeeper.services;

import com.eventkeeper.dao.EventDAO;
import com.eventkeeper.models.Event;

import java.util.Date;
import java.util.List;

public class EventService {
    private final EventDAO eventDAO;


    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }


    public void createEvent(Event event) {
        eventDAO.addEvent(event);
    }


    public void updateEvent(Event event) {
        eventDAO.updateEvent(event);
    }


    public void deleteEvent(int eventId) {
        eventDAO.deleteEvent(eventId);
    }


    public Event getEventById(int eventId) {
        return eventDAO.getEventById(eventId);
    }
    public List<Event> searchEvents(Date date, String location, String type) {
        return eventDAO.searchEvents(date, location, type);
    }

    public List<Event> getAllEvents() {
        return eventDAO.getAllEvents();
    }
}
