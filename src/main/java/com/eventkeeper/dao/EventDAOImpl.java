package com.eventkeeper.dao;

import com.eventkeeper.models.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventDAOImpl implements EventDAO {
    private static List<Event> events = new ArrayList<>();

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

    @Override
    public List<Event> searchEvents(Date date, String location, String type) {
        return events.stream()
                .filter(event -> (date == null || event.getDate().equals(date)) &&
                        (location == null || location.isEmpty() || event.getLocation().equalsIgnoreCase(location)) &&
                        (type == null || type.isEmpty() || event.getType().equalsIgnoreCase(type)))
                .collect(Collectors.toList());
    }
    public Event findByEventID(int eventID){
        return events.stream().filter(e-> e.getId() == eventID).findFirst().get();
    }




}

