package com.eventkeeper.services;

import com.eventkeeper.dao.EventDAO;
import com.eventkeeper.models.Event;

import java.util.List;

public class EventService {
    private final EventDAO eventDAO;

    // Injecting the EventDAO dependency into the constructor
    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    // Create a new event
    public void createEvent(Event event) {
        eventDAO.addEvent(event);
    }

    // Update an existing event
    public void updateEvent(Event event) {
        eventDAO.updateEvent(event);
    }

    // Delete an event by ID
    public void deleteEvent(int eventId) {
        eventDAO.deleteEvent(eventId);
    }

    // Get an event by ID
    public Event getEventById(int eventId) {
        return eventDAO.getEventById(eventId);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventDAO.getAllEvents();
    }
}
