
package com.eventkeeper.dao;

import com.eventkeeper.models.Event;
import com.eventkeeper.models.Participant;
import com.eventkeeper.models.Registration;

import java.util.ArrayList;
import java.util.List;


public class RegistrationDAO {
    private List<Registration> registrations = new ArrayList<>();

    public void registerParticipant(Participant participant, Event event) {
        // Vérifier si l'inscription existe déjà
        for (Registration reg : registrations) {
            if (reg.getParticipant().getId() == participant.getId() &&
                    reg.getEvent().getId() == event.getId()) {
                System.out.println("Participant déjà inscrit à cet événement.");
                return;
            }
        }
        registrations.add(new Registration(participant, event));
        System.out.println("Participant inscrit avec succès.");
    }

    public void unregisterParticipant(Participant participant, Event event) {
        registrations.removeIf(registration ->
                registration.getParticipant().getId() == participant.getId() &&
                        registration.getEvent().getId() == event.getId());
        System.out.println("Participant désinscrit avec succès.");
    }

    public List<Registration> getAllRegistrations() {
        return registrations;
    }

    public List<Event> getEventsForParticipant(Participant participant) {
        List<Event> events = new ArrayList<>();
        for (Registration registration : registrations) {
            if (registration.getParticipant().getId() == participant.getId()) {
                events.add(registration.getEvent());
            }
        }
        return events;
    }

    public List<Participant> getParticipantsForEvent(Event event) {
        List<Participant> participants = new ArrayList<>();
        for (Registration registration : registrations) {
            if (registration.getEvent().getId() == event.getId()) {
                participants.add(registration.getParticipant());
            }
        }
        return participants;
    }
}
