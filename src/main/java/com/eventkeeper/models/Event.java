package com.eventkeeper.models;

import java.util.Date;

public class Event {
    private static int lastId = 0;
    private int id;
    private String title;
    private String description;
    private Date date;
    private String location;
    private String type;

    // Constructeur sans paramètres
    public Event() {}

    // Constructeur avec paramètres (sans id, car il est auto-généré)
    public Event(String title, String description, Date date, String location, String type) {
        this.id = ++lastId; // Incrémente et assigne un nouvel ID unique
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.type = type;
    }

    // Getters et Setters
    public int getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
