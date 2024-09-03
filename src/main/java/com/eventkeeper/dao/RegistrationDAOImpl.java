package com.eventkeeper.dao;

import com.eventkeeper.models.Registration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    private final List<Registration> registrations = new ArrayList<>();

    @Override
    public void save(Registration registration) {
        registrations.add(registration);
    }

    @Override
    public void delete(int eventId, int participantId) {
        registrations.removeIf(r -> r.getEventId() == eventId && r.getParticipantId() == participantId);
    }

    @Override
    public List<Registration> findByEventId(int eventId) {
        List<Registration> result = new ArrayList<>();
        for (Registration registration : registrations) {
            if (registration.getEventId() == eventId) {
                result.add(registration);
            }
        }
        return result;
    }

    @Override
    public List<Registration> findByParticipantId(int participantId) {
        List<Registration> result = new ArrayList<>();
        for (Registration registration : registrations) {
            if (registration.getParticipantId() == participantId) {
                result.add(registration);
            }
        }
        return result;
    }


    @Override
    public void registerParticipant(int eventId, int participantId) {
        // Create a new registration
        Registration registration = new Registration(eventId, participantId);
        save(registration);
    }

    @Override
    public List<Registration> getRegistrationsByParticipantId(int participantId) {
        return findByParticipantId(participantId);
    }
}
