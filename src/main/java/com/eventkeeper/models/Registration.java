package com.eventkeeper.models;

public class Registration {
    private Event event;
    private Participant participant;

    public Registration(Event event, Participant participant) {
        this.event = event;
        this.participant = participant;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "event=" + event +
                ", participant=" + participant +
                '}';
    }
}
