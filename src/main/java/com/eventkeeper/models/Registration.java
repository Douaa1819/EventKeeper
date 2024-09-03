package com.eventkeeper.models;

public class Registration {
    private int eventId;
    private int participantId;

    public Registration(int eventId, int participantId) {
        this.eventId = eventId;
        this.participantId = participantId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    @Override
    public String toString() {
        return "Event ID: " + eventId + ", Participant ID: " + participantId;
    }
}
