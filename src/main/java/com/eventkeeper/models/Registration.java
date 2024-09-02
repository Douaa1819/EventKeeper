
package com.eventkeeper.models;


public class Registration {
    private Participant participant;
    private Event event;

    public Registration(Participant participant, Event event) {
        this.participant = participant;
        this.event = event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Registration{participant=" + participant + ", event=" + event + "}";
    }
}
