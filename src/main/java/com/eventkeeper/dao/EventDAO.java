
package com.eventkeeper.dao;

import com.eventkeeper.models.Event;
import java.util.Date;
import java.util.List;

public interface EventDAO {
    void addEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(int eventId);
    Event getEventById(int eventId);
    List<Event> getAllEvents();
    List<Event> searchEvents(Date date, String location, String type);
    }

